package com.ToyStory101.instagram.domain.User.controller;

import com.ToyStory101.instagram.domain.User.dto.AddUserRequest;
import com.ToyStory101.instagram.domain.User.dto.UserLoginRequest;
import com.ToyStory101.instagram.domain.User.entity.User;
import com.ToyStory101.instagram.domain.User.service.UserService;
import com.ToyStory101.instagram.global.dto.CODE;
import com.ToyStory101.instagram.global.dto.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/v1/member")
//session, cookie는 대체로 controller에서!

public class UserController {

    private final UserService userService;
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody Map<String,String> raw, HttpSession ses) {
//        User user = userService.Login(raw.get("email"), raw.get("password"));
//        return ResponseEntity.ok().body(user);
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request){
        User user = userService.Login(userLoginRequest);

        Result result = new Result();
        result.setCode(CODE.SUCCESS);
        result.setMessage("로그인 성공");
        result.setData(user);

        HttpSession session = request.getSession(true);
        session.setAttribute(user.getName(), user.getPassword());
        return ResponseEntity.ok().body(result);

    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody AddUserRequest addUserRequest){
        User user = userService.signup(addUserRequest);
//        userService.signup(addUserRequest);

//        return ResponseEntity.ok().body("완료");
        Result result = new Result();
        result.setCode(CODE.SUCCESS);
        result.setMessage("회원가입 성공");
        result.setData(user);

        return ResponseEntity.ok().body(result);

    }

}
