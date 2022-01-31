package com.example.springbootsampleproject.repositories.user;

import com.example.springbootsampleproject.entities.UserDTO;
import com.example.springbootsampleproject.entities.UserSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserRepositoryCustom {
    Page<UserDTO> findAllUsingQuerydsl(UserSearchCondition condition, Pageable pageable);
}
