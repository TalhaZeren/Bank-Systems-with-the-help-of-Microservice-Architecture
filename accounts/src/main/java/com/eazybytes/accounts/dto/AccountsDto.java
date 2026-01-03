package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold account information"
)
public class AccountsDto {
    @NotEmpty(message = "Account number cannot be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digit.")
    @Schema(
            description = "Account number of Talha Zeren's account", example = "53672476234"
    )
    private Long accountNumber;

    @NotEmpty(message = "Please, select your account type")

    @Schema(
            description = "Talha Zeren Account Type", example = "Savings"
    )
    private String accountType;

    @Schema(
            description = "Talha Zeren Branch Address", example = "123_NewYork Street"
    )
    @NotEmpty(message = "Branch Adress is mandatorty.")
    private String branchAddress;


}
