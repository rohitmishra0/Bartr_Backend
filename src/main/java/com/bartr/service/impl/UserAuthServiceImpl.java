package com.bartr.service.impl;

import com.bartr.model.User;
import com.bartr.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UserAuthServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            if(username == null){
                log.debug("loadUserByUsername : Username is null");
                throw new RuntimeException("Username is null in loadUserByUsername");
            }

            User user = userRepository.findByUsername(username).orElse(null);
            if (user == null) {
                log.debug("loadUserByUsername : user data is null in DB");
                throw new UsernameNotFoundException("User not found in DB");
            }
            System.out.println(username);
            UserDetails result = new org.springframework.security.core.userdetails.User(user.getUsername(),
                    user.getPassword(), AuthorityUtils.NO_AUTHORITIES);
            System.out.println(result);
            log.info("loadUserByUsername : User data is fetched from database and submitted to auth provider for authentication");
            return result;
        }catch (Exception e) {
            log.error("loadUserByUsername : {}",e.getMessage());
            throw new UsernameNotFoundException(e.getMessage());
        }
    }
}

