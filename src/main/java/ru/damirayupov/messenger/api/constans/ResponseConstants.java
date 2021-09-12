package ru.damirayupov.messenger.api.constans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public enum ResponseConstants {
    SUCCESS("success"), ERROR("error"),
    USERNAME_UNAVAILABLE("USR_001"),
    INVALID_USER_ID("USR_002"),
    EMPTY_STATUS("USR_003"),
    MESSAGE_EMPTY("MES_001"),
    MESSAGE_RECIPIENT_INVALID("MES_002"),
    ACCOUNT_DEACTIVATED("GLO_001");

    private String value;

    ResponseConstants(String value) {
        this.value = value;
    }
    public String getValue(){
        return value;
    }
}
