package com.example.bookflixspring.user;

import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;

import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;

@Table("users")
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @Generated
    private Long id;
    private String email;
    private String password;
    private Role role;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    public Role getRole(){
        return role;
    }
    
    public String getPassword() {
        return password;
    }

    public Long getId(){
        return id;
    }

    @Override
    public String getUsername() {
        return email;
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
        return true;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
}
