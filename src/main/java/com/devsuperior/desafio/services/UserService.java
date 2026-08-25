package com.devsuperior.desafio.services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devsuperior.desafio.projections.UserDetailsProjection;
import com.devsuperior.desafio.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        UserDetailsProjection projection =
                userRepository.searchUserAndRolesByEmail(username);

        if (projection == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new User(
                projection.getUsername(),
                projection.getPassword(),
                Collections.singleton(
                        new SimpleGrantedAuthority(projection.getAuthority())
                )
        );
    }
}