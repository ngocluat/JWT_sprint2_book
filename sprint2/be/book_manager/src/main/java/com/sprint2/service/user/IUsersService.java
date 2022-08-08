package com.sprint2.service.user;



import com.sprint2.dto.user.payload.SignUpRequest;
import com.sprint2.model.Users;

import java.util.List;

public interface IUsersService {



    void saveUsers(SignUpRequest signUpRequest);


    void saveUser(Users users);

    List<Users> checkEmail(String email);
}
