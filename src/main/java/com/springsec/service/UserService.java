//package com.springsec.service;
//
//import com.springsec.model.UserEntity;
//import com.springsec.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserEntity userEntity = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
//        
//        System.out.print(username);
//
//        return org.springframework.security.core.userdetails.User.builder()
//                .username(userEntity.getUsername())
//                .password(userEntity.getPassword())
//                .roles(userEntity.getRoles())
//                .build();
//    }
//}



package com.springsec.service;

import com.springsec.model.UserEntity;
import com.springsec.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        String encodedPassword = passwordEncoder.encode(userEntity.getPassword());
        
        // Print username and encoded password
        System.out.println("Username: " + userEntity.getUsername());
        System.out.println("Encoded Password: " + encodedPassword);

        return org.springframework.security.core.userdetails.User.builder()
                .username(userEntity.getUsername())
                .password(encodedPassword)
                .roles(userEntity.getRoles())
                .build();
    }


}
