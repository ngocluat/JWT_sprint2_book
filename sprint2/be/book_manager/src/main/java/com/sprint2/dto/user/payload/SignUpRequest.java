package com.sprint2.dto.user.payload;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.Period;

/**
 * Function: when user submit sign-up form, this class will use to take all field in sign-up form and send to url mapping method
 */
public class SignUpRequest implements Validator {

    private String name;
    private String email;
    private String password;
    private String confirmPassword;
    private Integer gender;
    private String address;
    private String phone;
    private String dayOfBirth;
    private String note;

    public SignUpRequest() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        SignUpRequest signUpRequest = (SignUpRequest) target;
        final String NAME = "name";
        final String EMAIL = "email";
        final String PASSWORD = "password";
        if (!signUpRequest.getName().matches("^(\\s?[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĐĨŨƠàáâãèéêếìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỂưạảấầẩẫậắằẳẵặẹẻẽềểễỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]\\s?){6,100}$")) {
            errors.rejectValue(NAME, "name.invalid.pattern", "Tên chứa kí tự đặc biệt");
        } else if (signUpRequest.getName().length() > 50) {
            errors.rejectValue(NAME, "name.invalid.maxLength", "Tên quá dài");
        } else if (signUpRequest.getName().length() < 5) {
            errors.rejectValue(NAME, "name.invalid.minLength", "Tên quá ngắn");
        }

        if (signUpRequest.getEmail() == null) {
            errors.rejectValue(EMAIL, "email.invalid.pattern", "Không được để trống");
        } else if (!signUpRequest.getEmail().matches("^[a-z0-9]((.)?[a-z0-9]){4,100}@g(oogle)?mail\\.com$")) {
            errors.rejectValue(EMAIL, "email.invalid.pattern", "Không đúng định dạng email");
        } else if (signUpRequest.getEmail().length() > 60) {
            errors.rejectValue(EMAIL, "", "Vượt quá độ dài cho phép");
        } else if (signUpRequest.getEmail().length() <= 12) {
            errors.rejectValue(EMAIL, "", "Quá ngắn vui lòng nhập lại");
        }

        if (signUpRequest.getPassword().contains(" ")) {
            errors.rejectValue(PASSWORD, "", "Có ký tự trống trong mật khẩu của bạn");
        } else if (!signUpRequest.getPassword().matches("^(\\s?[a-zA-Z_\\d]\\s?){6,100}$")) {
            errors.rejectValue(PASSWORD, "", "Có kí tự đặc biệt không được cho phép");
        } else if (signUpRequest.getPassword().length() > 60) {
            errors.rejectValue(PASSWORD, "", "Mật khẩu vượt quá độ dài cho phép");
        } else if (signUpRequest.getPassword().length() < 6) {
            errors.rejectValue(PASSWORD, "", "Mật khẩu quá ngắn");
        }

        if (!signUpRequest.getPhone().matches("((09)|(08)|(07))\\d{8}")) {
            errors.rejectValue("phone", "phone.invalid.pattern", "Số điện thoại phải bắt đầu bằng 09 08 hoặc 07");
        }

        LocalDate localDate = LocalDate.now();
        int age = Period.between(LocalDate.parse(signUpRequest.getDayOfBirth()), localDate).getYears();
        if (!signUpRequest.getDayOfBirth().matches("((19)(\\d){2}|(20)(([01]\\d)|[2][0-2]))-([0]\\d|[1][0-2])-([012]\\d|[3][0-1])$")) {
            errors.rejectValue("dayOfBirth", "date.invalid.pattern", "Sai định dạng ngày/tháng/năm");
        } else if (age < 18) {
            errors.rejectValue("dayOfBirth", "", "Quý khách chưa đủ 18 tuổi");
        }

        if (!signUpRequest.getConfirmPassword().matches(signUpRequest.getPassword())) {
            errors.rejectValue("confirmPassword", "confirmPassword.invalid", "Xác nhận mật khẩu không chính xác");
        }
    }
}
