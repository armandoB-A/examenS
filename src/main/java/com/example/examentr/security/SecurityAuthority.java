package com.example.examentr.security;

import com.example.examentr.entities.Rol;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class SecurityAuthority implements GrantedAuthority {

    private final Rol authority;

    @Override
    public String getAuthority() {
        return authority.getNombre();
    }
}
