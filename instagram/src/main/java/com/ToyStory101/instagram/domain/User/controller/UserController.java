package com.ToyStory101.instagram.domain.User.controller;

import com.ToyStory101.instagram.domain.User.dto.AddUserRequest;
import com.ToyStory101.instagram.domain.User.dto.UserLoginRequest;
import com.ToyStory101.instagram.domain.User.entity.User;
import com.ToyStory101.instagram.domain.User.service.UserService;
import com.ToyStory101.instagram.global.dto.CODE;
import com.ToyStory101.instagram.global.dto.Result;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);

        if(session != null){ // null이 아니라는건 기존에 세션이 존재했었다는 뜻이므로
            session.invalidate();
        }

//        Cookie myCookie = new Cookie("JSESSIONID",null);  // 쿠키 값을 null로 설정
//        myCookie.setMaxAge(0);
//        response.addCookie(myCookie);

        Result result = new Result();
        result.setCode(CODE.SUCCESS);
        result.setMessage("로그아웃 완료");
        result.setData(null);

        return ResponseEntity.ok().body(result);

    }

    @PostMapping("/join")
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
