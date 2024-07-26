package dev.bengi.authservice.dto;

import dev.bengi.userservice.enumeration.RoleEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * A record for authenticated user using spring security
 *
 * @author bengi
 */
public record AuthenticatedUser(

        Integer userId,
        String email,
        String password,
        RoleEnum role) implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /**
         * switch case of role
         */
        return switch (this.role) {
            case ADMIN -> List.of(new SimpleGrantedAuthority(RoleEnum.ADMIN.name()));
            case SALE -> List.of(new SimpleGrantedAuthority(RoleEnum.SALE.name()));
            case EDITOR -> List.of(new SimpleGrantedAuthority(RoleEnum.EDITOR.name()));
            case SHIPPER -> List.of(new SimpleGrantedAuthority(RoleEnum.SHIPPER.name()));
            case ASSIST -> List.of(new SimpleGrantedAuthority(RoleEnum.ASSIST.name()));
        };
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
