package com.example.api_ciclovidaprod_security.WebSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //para APIs lo sacamos
                .authorizeRequests()
                .antMatchers("/","/index.html").permitAll()
                .antMatchers(HttpMethod.DELETE,"api/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(5);
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails usuario1 = User.builder()
                .username("agus")
                .password("clave")
                .roles(UserRole.ADMIN.name())
                .build();
        UserDetails usuario2 = User.builder()
                .username("rick")
                .password("clave")
                .roles(UserRole.CLIENTE.name())
                .build();
        UserDetails usuario3 = User.builder()
                .username("pepe")
                .password("clave")
                .roles(UserRole.CLIENTE.name())
                .build();

        return new InMemoryUserDetailsManager(usuario1,usuario2,usuario3);

    }
}
