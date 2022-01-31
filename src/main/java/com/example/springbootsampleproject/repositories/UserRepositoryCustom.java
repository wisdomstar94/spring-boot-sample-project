package com.example.springbootsampleproject.repositories;

import com.example.springbootsampleproject.entities.UserDTO;

import java.util.List;

public interface UserRepositoryCustom {
    List<UserDTO> findAllUsingQuerydsl();
}
