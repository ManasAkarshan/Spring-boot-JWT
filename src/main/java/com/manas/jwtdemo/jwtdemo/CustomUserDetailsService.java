package com.manas.jwtdemo.jwtdemo;

import com.manas.jwtdemo.jwtdemo.model.UserEntity;
import com.manas.jwtdemo.jwtdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(username);
        UserEntity userEntity = userEntityOptional.orElseThrow(()->
                new UsernameNotFoundException("User not found: "+ username));

        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        // Return a UserDetails object with user info, password, and roles
        return new User(userEntity.getUsername(), userEntity.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
