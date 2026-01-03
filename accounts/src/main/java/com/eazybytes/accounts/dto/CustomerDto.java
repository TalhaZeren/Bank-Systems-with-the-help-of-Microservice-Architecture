package com.eazybytes.accounts.dto;

import com.eazybytes.accounts.entity.Accounts;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "The Schema to hold Customer and Account information."
)
public class CustomerDto {

    @Schema(
            description = "Name of Customer", example = "Talha Zeren"
    )
    @NotEmpty(message = "Name cannot be a null or empty")
    @Size(min = 5,max = 30, message ="the length of the customer name should be between 5 and 30")
    private String name;

    @Schema(
            description = "e-mail of Customer", example = "talhazeren97@gmail.com"
    )
    @NotEmpty(message = "email cannot be a null or empty")
    @Email(message = "email address should be a valid value")
    private String email;

    @Schema(
            description = "mobile number of Customer", example = "2394723947"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digit.")
    private String mobileNumber;

    @Schema(
            description = "Account detail of Customer"
    )
    private AccountsDto accountsDto;

}
