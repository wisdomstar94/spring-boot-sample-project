package com.example.springbootsampleproject.contollers;

import com.example.springbootsampleproject.dtos.responses.LoginResponseInfo;
import com.example.springbootsampleproject.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController {
    private JwtService jwtService;

    public LoginController(@Autowired JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("")
    public @ResponseBody LoginResponseInfo index() {
        String accessToken = this.jwtService.createToken("user", "1", 24);

        LoginResponseInfo responseInfo = new LoginResponseInfo();

        responseInfo.setCode(10001000);
        responseInfo.setMsg("success");
        responseInfo.setAccessToken(accessToken);

        return responseInfo;
    }

}
