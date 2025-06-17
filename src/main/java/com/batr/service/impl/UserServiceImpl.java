package com.batr.service.impl;

import com.batr.model.User;
import com.batr.repository.UserRepository;
import com.batr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public User registerUSer(String email){

    }

}
