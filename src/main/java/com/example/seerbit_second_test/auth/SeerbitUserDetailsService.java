package com.example.seerbit_second_test.auth;

import com.example.seerbit_second_test.model.User;
import com.example.seerbit_second_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class SeerbitUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        if(null != user)throw new UsernameNotFoundException("Could not find User with email = " + email);

        return new org.springframework.security.core.userdetails.User(email,user.getPassword(),new ArrayList<>());
    }
}
