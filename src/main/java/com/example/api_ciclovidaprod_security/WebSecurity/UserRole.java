package com.example.api_ciclovidaprod_security.WebSecurity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.api_ciclovidaprod_security.WebSecurity.UserPermission.*;

public enum UserRole {
    ADMIN(Set.of(USER_WRITE, USER_READ, PRODUCTO_READ, PRODUCTO_WRITE
            )),
    CLIENTE(Set.of(USER_WRITE, USER_READ, PRODUCTO_READ
    ));

    private final Set<UserPermission> permission;
    UserRole (Set<UserPermission>permissions){
        this.permission = permissions;
    }

    public Set<UserPermission> getPermission(){
        return permission;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permisos = getPermission().stream()
                .map(permission-> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
                permisos.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
                return permisos;
    }
}
