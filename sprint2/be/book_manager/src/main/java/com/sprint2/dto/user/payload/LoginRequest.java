package com.sprint2.dto.user.payload;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * Function: use for login request when user submit sing in
 */
public class LoginRequest implements Validator {

    @Length(min = 5,message = "Tên tài khoản quá ngắn.")
    @Length(max = 50, message = "Tên tài khoản quá dài.")
    @Email(message = "Không đúng định dạng email.")
    @NotEmpty(message = "Không bỏ trống.")
    private String username;
    @Length(min=5,message = "Mật khẩu quá ngắn.")
    @Length(max=50,message = "Mật khẩu quá dài.")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        LoginRequest loginRequest = (LoginRequest) target;
        if(loginRequest.getPassword().contains(" ")){
            errors.rejectValue("password","","Mật khẩu chứa kí tự trống.");
        }else if(!loginRequest.getPassword().matches("^(\\s?[a-zA-Z!@#$%^&*()\\d]\\s?){6,50}$")){
            errors.rejectValue("password","","Có kí tự đặc biệt không được cho phép.");
        }
    }
}
