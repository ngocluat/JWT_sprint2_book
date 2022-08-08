package com.sprint2.service.user.impl;


import com.sprint2.model.Users;
import com.sprint2.repository.user.IUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersDetailsService implements UserDetailsService {

    @Autowired
    private IUsersRepository iUsersRepository;
    /**
     * Function: this method used to check username when someone login and give back all information and List user authority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = this.iUsersRepository.getUserByUsername(username);
        if(users == null){
            throw new UsernameNotFoundException("Not found user with username: "+username);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (int i = 0; i < users.getUserRoleList().size(); i++) {
            authorities.add( new SimpleGrantedAuthority(users.getUserRoleList().get(i).getRoles().getRoleName()));
        }
        return new User(users.getUsername(),
                users.getPassword(),
                true,true,true,true,
                authorities);
    }
}
