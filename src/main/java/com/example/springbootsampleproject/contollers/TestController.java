package com.example.springbootsampleproject.contollers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("")
    public @ResponseBody String index() {
        this.logger.info("TestController 진입!");
        return "This is Test Page!";
    }

    @GetMapping("/test2")
    public @ResponseBody String test2() {
        this.logger.info("TestController 진입!");
        return "This is Test2 Page!";
    }
}
