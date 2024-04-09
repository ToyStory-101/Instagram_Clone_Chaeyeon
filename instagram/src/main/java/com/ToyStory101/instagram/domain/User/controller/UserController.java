package com.ToyStory101.instagram.domain.User.controller;

import com.ToyStory101.instagram.domain.User.entity.User;
import com.ToyStory101.instagram.domain.User.service.UserService;
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
public class UserController {

    private final UserService userService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> raw, HttpSession ses) {
        User user = userService.Login(raw.get("email"), raw.get("password"));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Map<String,String> raw){
        userService.signup(raw.get("email"),raw.get("password"));
        return ResponseEntity.ok().body("완료");
    }
}
