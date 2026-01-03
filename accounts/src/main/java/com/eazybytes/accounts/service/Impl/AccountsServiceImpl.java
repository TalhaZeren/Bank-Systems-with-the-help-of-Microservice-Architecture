package com.eazybytes.accounts.service.Impl;

import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.CustomerAlreadyExistsException;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountsMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private CustomerRepository customerRepository;
    private AccountsRepository accountsRepository;

    // Mapper process has been used for convertion.
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer newCustomer = CustomerMapper.mapToCustomer(customerDto,new Customer());
        Optional<Customer> optionalCustomer =  customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already registered with given mobile number"
                    + customerDto.getMobileNumber());
        }
        Customer savedCustomer = customerRepository.save(newCustomer);
        // new customer information has been sent to "createNewAccounts" method below and saved to database.
        accountsRepository.save((createNewAccounts(savedCustomer)));
    }

    // After creating customer, an account has been created for new customer below.
    private Accounts createNewAccounts(Customer customer){
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getId());
        // creating range
        long randAccNumber = 1000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(randAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getId()).orElseThrow(
                ()-> new ResourceNotFoundException("Account","Customer ID",customer.getId().toString())
        );
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer,new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts,new AccountsDto()));
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto != null) {
        Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Account","Account Number",accountsDto.getAccountNumber().toString())
        );
        AccountsMapper.mapToAccounts(accountsDto,accounts);
        accountsRepository.save(accounts);

        Long customerId =  accounts.getCustomerId();
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new ResourceNotFoundException("Customer","Customer ID",customerId.toString())
        );
        CustomerMapper.mapToCustomer(customerDto,customer);
        customerRepository.save(customer);
        isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getId()); // We take care of this process because this is not belong to JPA.
        customerRepository.deleteById(customer.getId());
        return true;
    }


}
