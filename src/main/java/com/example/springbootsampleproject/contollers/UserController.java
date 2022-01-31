package com.example.springbootsampleproject.contollers;

import com.example.springbootsampleproject.dtos.requests.AddUserRequestInfo;
import com.example.springbootsampleproject.dtos.requests.FindAllUserRequestInfo;
import com.example.springbootsampleproject.dtos.responses.BasicResponseInfo;
import com.example.springbootsampleproject.dtos.responses.ListResponseInfo;
import com.example.springbootsampleproject.entities.User;
import com.example.springbootsampleproject.entities.UserDTO;
import com.example.springbootsampleproject.entities.UserSearchCondition;
import com.example.springbootsampleproject.services.UserService;
import com.querydsl.core.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public @ResponseBody ListResponseInfo<UserDTO> list(
        @RequestParam(value = "seq", defaultValue = "0") Integer seq,
        @RequestParam(value = "createStartAt", defaultValue = "0") Long createAtStart,
        @RequestParam(value = "userName", defaultValue = "") String userName,
        @PageableDefault(page = 1, size = 10) Pageable pageable
//        @RequestParam(value = "page", defaultValue = "") Integer page,
//        @RequestParam(value = "size", defaultValue = "") Integer size,
//        @RequestParam(value = "sort", defaultValue = "") List<String> sort
    ) {
//        Pageable pageable = PageRequest.of(page, size, );

        UserSearchCondition condition = new UserSearchCondition();
        condition.setSeq(seq);
//        condition.setUserKey(req.getUserKey());
        condition.setUserName(userName);
        condition.setCreateStartAt(createAtStart);
//        condition.setCreateEndAt(req.getCreateEndAt());

        Page<UserDTO> pageInfo = this.userService.getAllListUsingQuerydsl(condition, pageable);

        Integer totalPages = pageInfo.getTotalPages();
        Long totalElementCount = pageInfo.getTotalElements();
        List<UserDTO> list = pageInfo.getContent();
        Integer currentElementCount = list.size();

        ListResponseInfo<UserDTO> responseInfo = new ListResponseInfo();
        responseInfo.setCode(10001000);
        responseInfo.setMsg("success");
        responseInfo.setData(list);
        responseInfo.setTotalPages(totalPages);
        responseInfo.setCurrentElementCount(currentElementCount);
        responseInfo.setTotalElementCount(totalElementCount);
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
