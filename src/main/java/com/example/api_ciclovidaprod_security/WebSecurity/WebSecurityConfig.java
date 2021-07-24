package com.example.api_ciclovidaprod_security.WebSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.api_ciclovidaprod_security.WebSecurity.UserPermission.PRODUCTO_WRITE;
import static com.example.api_ciclovidaprod_security.WebSecurity.UserRole.ADMIN;
import static com.example.api_ciclovidaprod_security.WebSecurity.UserRole.CLIENTE;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //para APIs lo sacamos
                .authorizeRequests()

                .antMatchers("/","/index.html").permitAll()
                //.antMatchers(HttpMethod.DELETE,"api/**").hasRole(ADMIN.getRole())               //Podemos poner acá que nos permite hacer cada rol o permiso, o en el controller
                //.antMatchers(HttpMethod.POST, "api/productos").hasAuthority(PRODUCTO_WRITE.getPermission())
                //.antMatchers(HttpMethod.PUT,"api/productos").hasAnyRole(ADMIN.getRole())


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
                .password(passwordEncoder().encode("clave"))
                //.roles(UserRole.ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();
        UserDetails usuario2 = User.builder()
                .username("rick")
                .password(passwordEncoder().encode("clave"))
                .authorities(CLIENTE.getGrantedAuthorities())
                .build();
        UserDetails usuario3 = User.builder()
                .username("pepe")
                .password(passwordEncoder().encode("clave"))
                .authorities(CLIENTE.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(usuario1,usuario2,usuario3);
    }
}
