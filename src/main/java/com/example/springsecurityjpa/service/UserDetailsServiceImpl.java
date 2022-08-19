package com.example.springsecurityjpa.service;

import com.example.springsecurityjpa.model.AppUser;
import com.example.springsecurityjpa.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user = appUserRepository.findByUserName(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not Found " + username));

        return user.map(AppUserDetails::new).get();
    }
}
