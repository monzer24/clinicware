package com.clinicware.data;

import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@Data
public class RegisterValidation {

    public RegisterValidation() {
        id = -1;
    }

    public RegisterValidation(int id, int status, String errorMessage) {
        this.id = id;
        this.status = status;
        this.errorMessage = errorMessage;
    }

    private int id;
    private int status;
    private String errorMessage;

    public static enum validVars {
        P_ID, P_TRX_STATUS, P_ERROR_MESSAGE
    }
}
