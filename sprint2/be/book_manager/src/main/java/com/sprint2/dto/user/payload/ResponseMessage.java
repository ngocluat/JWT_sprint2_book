package com.sprint2.dto.user.payload;

import java.util.List;
import java.util.Map;

/**
 * Created by HuuNQ
 * Time 12:00 30/06/2022
 * Function: use for check validate form
 */
public class ResponseMessage <T> {
    private boolean status;
    private String message;
    private Map<String,String> errorMap;
    private List<T> data;

    public ResponseMessage() {
    }

    public ResponseMessage(boolean status, String message, List<T> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseMessage(boolean status, String message, Map<String, String> errorMap, List<T> data) {
        this.status = status;
        this.message = message;
        this.errorMap = errorMap;
        this.data = data;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }

    public void setErrorMap(Map<String, String> errorMap) {
        this.errorMap = errorMap;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
