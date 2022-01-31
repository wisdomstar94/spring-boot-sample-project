package com.example.springbootsampleproject.dtos.requests;

import com.example.springbootsampleproject.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AddUserRequestInfo {
    List<User> users;
    Integer page;
    Integer size;
}
