package com.example.springbootsampleproject.services;

import com.example.springbootsampleproject.entities.User;
import com.example.springbootsampleproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(
            @Autowired UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    public List<User> getAllList() {
        List<User> list = this.userRepository.findAll();
        return list;
    }

    public void addUsers(List<User> users) {
        this.userRepository.saveAll(users);
    }

}