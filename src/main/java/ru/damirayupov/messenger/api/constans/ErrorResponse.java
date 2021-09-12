package ru.damirayupov.messenger.api.constans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorResponse {
    private String errorCode;
    private String errorMessage;
}
