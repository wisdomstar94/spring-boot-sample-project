package com.example.springbootsampleproject.contollers;

import com.example.springbootsampleproject.dtos.responses.LoginResponseInfo;
import com.example.springbootsampleproject.dtos.responses.RefreshResponseInfo;
import com.example.springbootsampleproject.entities.User;
import com.example.springbootsampleproject.securities.JwtTokenProvider;
import com.example.springbootsampleproject.services.JwtService;
import com.example.springbootsampleproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController {
    private JwtService jwtService;
    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;

    public LoginController(
        @Autowired JwtService jwtService,
        @Autowired JwtTokenProvider jwtTokenProvider,
        @Autowired UserService userService
    ) {
        this.jwtService = jwtService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("")
    public @ResponseBody LoginResponseInfo index() {
        String userKey = "2";
        User userInfo = userService.getUserInfo(userKey);
//        String accessToken = this.jwtService.createToken("user", "1", 24);
        String accessToken = jwtTokenProvider.createToken(userInfo.getUserKey(), userInfo.getRoles());

        LoginResponseInfo responseInfo = new LoginResponseInfo();

        responseInfo.setCode(10001000);
        responseInfo.setMsg("success");
        responseInfo.setAccessToken(accessToken);

        return responseInfo;
    }

    @PostMapping("/refresh")
    public @ResponseBody RefreshResponseInfo refresh(
        @RequestHeader(value = "Authorization", defaultValue = "") String authorization
    ) {
        RefreshResponseInfo responseInfo = new RefreshResponseInfo();
        return responseInfo;
    }
}
