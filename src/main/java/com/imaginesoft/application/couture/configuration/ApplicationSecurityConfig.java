package com.imaginesoft.application.couture.configuration;


import com.imaginesoft.application.couture.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.imaginesoft.application.couture.model.LoginRole.*;
import static com.imaginesoft.application.couture.util.ApplicationDataFactory.API_V1_ADMIN;
import static com.imaginesoft.application.couture.util.ApplicationDataFactory.API_V1_APPLICATION;

@EnableWebSecurity
@Configuration
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final LoginService service;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(LoginService service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*")
                .permitAll()
                // order
                .antMatchers(HttpMethod.GET, API_V1_APPLICATION + "/orders/**").hasAnyRole(ADMIN.name(), MANAGER.name(), EMPLOYEE.name())
                .antMatchers(HttpMethod.POST, API_V1_APPLICATION + "/orders/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                .antMatchers(HttpMethod.PUT, API_V1_APPLICATION + "/orders/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                .antMatchers(HttpMethod.DELETE, API_V1_APPLICATION + "/orders/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                // model type
                .antMatchers(HttpMethod.GET, API_V1_APPLICATION + "/model-types/**").hasAnyRole(ADMIN.name(), MANAGER.name(), EMPLOYEE.name())
                .antMatchers(HttpMethod.POST, API_V1_APPLICATION + "/model-types/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                .antMatchers(HttpMethod.PUT, API_V1_APPLICATION + "/model-types/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                .antMatchers(HttpMethod.DELETE, API_V1_APPLICATION + "/model-types/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                // measure type
                .antMatchers(HttpMethod.GET, API_V1_APPLICATION + "/measure-types/**").hasAnyRole(ADMIN.name(), MANAGER.name(), EMPLOYEE.name())
                .antMatchers(HttpMethod.POST, API_V1_APPLICATION + "/measure-types/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                .antMatchers(HttpMethod.PUT, API_V1_APPLICATION + "/measure-types/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                .antMatchers(HttpMethod.DELETE, API_V1_APPLICATION + "/measure-types/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                // measure
                .antMatchers( API_V1_APPLICATION + "/measures/**").hasAnyRole(ADMIN.name(), MANAGER.name(), EMPLOYEE.name())
                // material types
                .antMatchers(HttpMethod.GET, API_V1_APPLICATION + "/material-types/**").hasAnyRole(ADMIN.name(), MANAGER.name(), EMPLOYEE.name())
                .antMatchers(HttpMethod.POST, API_V1_APPLICATION + "/material-types/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                .antMatchers(HttpMethod.PUT, API_V1_APPLICATION + "/material-types/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                .antMatchers(HttpMethod.DELETE, API_V1_APPLICATION + "/material-types/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                // login
                .antMatchers(HttpMethod.GET, API_V1_ADMIN + "/logins/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                .antMatchers(HttpMethod.POST, API_V1_ADMIN + "/logins/**").hasRole(ADMIN.name())
                .antMatchers(HttpMethod.PUT, API_V1_ADMIN + "/logins/**").hasRole(ADMIN.name())
                .antMatchers(HttpMethod.DELETE, API_V1_ADMIN + "/logins/**").hasRole(ADMIN.name())
                // employee
                .antMatchers(API_V1_ADMIN + "/employees/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                // dress type
                .antMatchers(HttpMethod.GET, API_V1_APPLICATION + "/dress-types/**").hasAnyRole(ADMIN.name(), MANAGER.name(), EMPLOYEE.name())
                .antMatchers(HttpMethod.POST, API_V1_APPLICATION + "/dress-types/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                .antMatchers(HttpMethod.PUT, API_V1_APPLICATION + "/dress-types/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                .antMatchers(HttpMethod.DELETE, API_V1_APPLICATION + "/dress-types/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                // dress
                .antMatchers(HttpMethod.GET, API_V1_APPLICATION + "/dresses/**").hasAnyRole(ADMIN.name(), MANAGER.name(), EMPLOYEE.name())
                .antMatchers(HttpMethod.POST, API_V1_APPLICATION + "/dresses/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                .antMatchers(HttpMethod.PUT, API_V1_APPLICATION + "/dresses/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                .antMatchers(HttpMethod.DELETE, API_V1_APPLICATION + "/dresses/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                // client
                .antMatchers(HttpMethod.GET, API_V1_APPLICATION + "/clients/**").hasAnyRole(ADMIN.name(), MANAGER.name(), EMPLOYEE.name())
                .antMatchers(HttpMethod.POST, API_V1_APPLICATION + "/clients/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                .antMatchers(HttpMethod.PUT, API_V1_APPLICATION + "/clients/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                .antMatchers(HttpMethod.DELETE, API_V1_APPLICATION + "/clients/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authentication) throws Exception {
        authentication
                .userDetailsService(service)
                .passwordEncoder(passwordEncoder);
    }
}
