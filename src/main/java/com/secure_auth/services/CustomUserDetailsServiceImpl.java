package com.secure_auth.services;

import com.secure_auth.configs.SecurityConfig;
import com.secure_auth.entities.models.AppUser;
import com.secure_auth.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    private final SecurityConfig securityConfig;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + username));

        return User.builder()
                .username(appUser.getEmail())
                .password(appUser.getPassword())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority(appUser.getRole())))
                .build();
    }

}
