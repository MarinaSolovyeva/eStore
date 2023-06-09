package com.example.estote.security;

import com.example.estote.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class UserDetail implements UserDetails {

    private final User user;

    public UserDetail(User user) {
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    public String getNameSurname() {
        return this.user.getNameSurname();
    }

    public String getEmailUser() {
        return this.user.getEmailUser();
    }

    public Date getDateBirth() {
        return this.user.getDateBirth();
    }

    public Long getId(){
        return this.user.getId();
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

    /**
     * @return authenticated user. it helps to get info about user
     */
    public User getPerson() {
        return this.user;
    }
}