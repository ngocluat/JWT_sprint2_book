package com.sprint2.controller;


import com.sprint2.dto.user.payload.JwtResponse;
import com.sprint2.dto.user.payload.LoginRequest;
import com.sprint2.dto.user.payload.ResponseMessage;
import com.sprint2.dto.user.payload.SignUpRequest;
import com.sprint2.service.user.IRoleService;
import com.sprint2.service.user.IUserRoleService;
import com.sprint2.service.user.IUsersService;
import com.sprint2.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/manager-security/users")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IUsersService iUsersService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private IUserRoleService iUserRoleService;

    @Autowired
    private IRoleService iRoleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * Function Sign-in Account Online and when success login server will return information with JWT
     */
    @PostMapping("/sign-in")
    public ResponseEntity<Object> getSignIn(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        new LoginRequest().validate(loginRequest, bindingResult);
        Map<String, String> errorMap = new HashMap<>();
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors()
                    .forEach(
                            err -> errorMap.put(err.getField(), err.getDefaultMessage())
                    );
            return ResponseEntity.badRequest().body(new ResponseMessage<>(false, "Failed", errorMap, new ArrayList<>()));
        }

        if (this.iUsersService.checkEmail(loginRequest.getUsername()).isEmpty()) {
            errorMap.put("notExists", "Tài khoản chưa tồn tại trong hệ thống");
            return ResponseEntity.badRequest().body(new ResponseMessage<>(false, "Failed", errorMap, new ArrayList<>()));
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtKey(authentication);
        User user = (User) authentication.getPrincipal();
        List<String> role = user.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(
                jwt, loginRequest.getUsername(), loginRequest.getPassword(), role)
        );
    }

    /**
     * Function Sign Up Online And Validate Sign Up Form
     */
    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUpUser(@Valid @RequestBody SignUpRequest signUpRequest, BindingResult bindingResult) {
        new SignUpRequest().validate(signUpRequest, bindingResult);
        Map<String, String> errorMap = new HashMap<>();
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors()
                    .forEach(
                            err -> errorMap.put(err.getField(), err.getDefaultMessage())
                    );
            return ResponseEntity.badRequest().body(new ResponseMessage<>(false, "Failed!", errorMap, new ArrayList<>()));
        }
        if (!this.iUsersService.checkEmail(signUpRequest.getEmail()).isEmpty()) {
            errorMap.put("email", "Email đã được sử dụng!");
            return ResponseEntity.badRequest().body(new ResponseMessage<>(false, "Failed!", errorMap, new ArrayList<>()));
        }
        try {
            this.iUsersService.saveUsers(signUpRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
