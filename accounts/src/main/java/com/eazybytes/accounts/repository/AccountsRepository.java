package com.eazybytes.accounts.repository;

import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.entity.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {
    Optional<Accounts> findByCustomerId(Long customerId);

    @Modifying
    @Transactional
    void deleteByCustomerId(Long customerId);
    /*
    * Note : We need to describe both these annotations. Because that is the function that we have created.So we
    * should say to JPA like it will take care of all process.
    * */

}
