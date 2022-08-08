package com.sprint2.service.user.impl;


import com.sprint2.dto.user.payload.SignUpRequest;
import com.sprint2.model.Customer;
import com.sprint2.model.Roles;
import com.sprint2.model.UserRole;
import com.sprint2.model.Users;
import com.sprint2.repository.ICustomerRepository;
import com.sprint2.repository.user.IRoleRepository;
import com.sprint2.repository.user.IUserRoleRepository;
import com.sprint2.repository.user.IUsersRepository;
import com.sprint2.service.user.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class UsersService implements IUsersService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IUsersRepository iUsersRepository;
    @Autowired
    private ICustomerRepository iCustomerRepository;

    @Autowired
    private IUserRoleRepository iUserRoleRepository;

    @Autowired
    private IRoleRepository iRoleRepository;


    /**
     * Function format Date, and save data for customer when sign up
     */
    @Override
    public void saveUsers(SignUpRequest signUpRequest) {
            Users users = new Users(signUpRequest.getEmail(),passwordEncoder.encode(signUpRequest.getPassword()));
            users.setFlag(true);
            Customer customer = new Customer();
            customer.setCustomerName(signUpRequest.getName());
            customer.setCustomerBirthday(signUpRequest.getDayOfBirth());
            customer.setCustomerAddress(signUpRequest.getAddress());
            customer.setCustomerNote(signUpRequest.getNote());
            customer.setCustomerPhone(signUpRequest.getPhone());
            customer.setCustomerGender(signUpRequest.getGender());
            customer.setCustomerUsername(users);
            customer.setFlag(true);
            Roles roles = this.iRoleRepository.findRolesByRoleName("ROLE_USER");
            UserRole userRole = new UserRole(users,roles);
            this.iUsersRepository.save(users);
            this.iUserRoleRepository.save(userRole);
            this.iCustomerRepository.save(customer);
    }



    @Override
    public void saveUser(Users users) {
        this.iUsersRepository.save(users);
    }


    @Override
    public List<Users> checkEmail(String email) {
        return this.iUsersRepository.checkEmail(email);
    }
}
