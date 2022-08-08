package com.sprint2.service.user.impl;

import com.sprint2.model.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Function this class use to implement UserDetails interface. this thing for get user when someone login
 */

public class UsersDetails implements UserDetails {
    private String username;
    private Boolean flag;
    private String password;
    private List<GrantedAuthority> authorities = null;
    private transient Map<String, Object> attributes;



    public UsersDetails(String username, Boolean flag, String password, List<GrantedAuthority> authorities) {
        this.username = username;
        this.flag = flag;
        this.password = password;
        this.authorities = authorities;
    }

    /**
     * Function: this method used to get All Authority of user and return Object UsersDetails
     */
    public static UsersDetails build(Users users){
        List<GrantedAuthority> authorities = users.getUserRoleList()
                .stream().map( role -> new SimpleGrantedAuthority(role.getRoles().getRoleName()))
                .collect(Collectors.toList());
        return  new UsersDetails(
                users.getUsername(),
                users.isFlag(),
                users.getPassword(),
                authorities);
    }

    /**
     * Function: create new User with Map attributes
     */
    public static UsersDetails create(Users users, Map<String, Object> attributes){
        UsersDetails userDetails = UsersDetails.build(users);
        userDetails.setAttributes(attributes);
        return userDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return flag;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsersDetails)) return false;
        UsersDetails that = (UsersDetails) o;
        return username.equals(that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }


}
