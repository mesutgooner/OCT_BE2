package com.globits.da.dto;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ResponseDTO {
    private Object data;
    private String errorCode;
    private String errorMessage;

    public ResponseDTO() {
    }

    public ResponseDTO(Object data, String errorCode, String errorMessage) {
        this.data = data;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
