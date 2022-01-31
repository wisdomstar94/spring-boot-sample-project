package com.example.springbootsampleproject.contollers;

import com.example.springbootsampleproject.dtos.requests.AddUserRequestInfo;
import com.example.springbootsampleproject.dtos.responses.BasicResponseInfo;
import com.example.springbootsampleproject.dtos.responses.ListResponseInfo;
import com.example.springbootsampleproject.entities.User;
import com.example.springbootsampleproject.entities.UserDTO;
import com.example.springbootsampleproject.services.UserService;
import com.querydsl.core.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public @ResponseBody ListResponseInfo<UserDTO> list() {
        List<UserDTO> list = this.userService.getAllListUsingQuerydsl();

        ListResponseInfo<UserDTO> responseInfo = new ListResponseInfo();
        responseInfo.setCode(10001000);
        responseInfo.setMsg("success");
        responseInfo.setData(list);
        return responseInfo;
    }

    @PutMapping("")
    public @ResponseBody BasicResponseInfo addUser(@RequestBody AddUserRequestInfo req) {
        this.userService.addUsers(req.getUsers());
        BasicResponseInfo responseInfo = new BasicResponseInfo();
        responseInfo.setCode(10001000);
        responseInfo.setMsg("success");
        return responseInfo;
    }
}
