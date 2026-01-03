package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@Schema(
        name = "ErrorResponse",
        description = "Schema to hold Error response information."
)
@Data @AllArgsConstructor
public class ErrorResponseDto {

    @Schema(
            description = "API path invoked by client")
    private String apiPath;

    @Schema(
            description = "Error code represented the error happened.")
    private HttpStatus errorCode;

    @Schema(
            description = "Error message indicated the error reason")
    private String errorMessage;

    private LocalDateTime errorTime;


}
