package com.example.demo.services;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository; 
    }

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        User user = userRepository.findByCpf(cpf).get();
        if(user == null)
            throw new UsernameNotFoundException("No user found with this cpf");
        //List<String> roles = Arrays.asList(user.getRole());
        UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                        .username(user.getCpf())
                        .password(user.getPassword())
                        .roles("USER")
                        .build();

        return userDetails;
    }
}