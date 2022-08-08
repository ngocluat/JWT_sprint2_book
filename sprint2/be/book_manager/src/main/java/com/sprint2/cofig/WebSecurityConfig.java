package com.sprint2.cofig;

import com.sprint2.service.user.impl.UsersDetailsService;
import com.sprint2.utils.AuthEntryPointJwt;
import com.sprint2.utils.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsersDetailsService usersDetailService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Autowired
    public AuthTokenFilter authTokenFilter;

    /**
     * Function : this method use to encode password
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usersDetailService).passwordEncoder(passwordEncoder());
    }

    /**
     * Function Create Bean AuthenticationManager
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Override method configure to config
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /**
         * @Function Config role to access api url
         */
        http.cors().and()
                .csrf()
                .disable()
                .authorizeRequests()
                // tất cả đường dẩn này đều và được
                .antMatchers("/api/manager-security/users/**", "/api/manager-book/**"
                )
                .permitAll()
                // dành cho admin
                .antMatchers("/admin/test/random/**"
                ).hasRole("ADMIN");
//                .hasAnyRole("EMPLOYEE", "MANAGER")
//                .hasRole("MANAGER")
//                .anyRequest()
//                .authenticated()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(unauthorizedHandler)
//                .and()
//                .rememberMe()
//                .tokenRepository(persistentTokenRepository())
//                .tokenValiditySeconds(computeDurationInMilliseconds()).and().logout().logoutSuccessUrl("/");
//
        http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

    }

    public int computeDurationInMilliseconds() {
        return (60 * 60 * 60);
    }

    private PersistentTokenRepository persistentTokenRepository() {
        return new InMemoryTokenRepositoryImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}