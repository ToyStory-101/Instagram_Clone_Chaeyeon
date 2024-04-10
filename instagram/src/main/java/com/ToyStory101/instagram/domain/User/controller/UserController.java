package com.ToyStory101.instagram.domain.User.controller;

import com.ToyStory101.instagram.domain.User.dto.AddUserRequest;
import com.ToyStory101.instagram.domain.User.dto.UserLoginRequest;
import com.ToyStory101.instagram.domain.User.entity.User;
import com.ToyStory101.instagram.domain.User.service.UserService;
import com.ToyStory101.instagram.global.dto.CODE;
import com.ToyStory101.instagram.global.dto.Result;
import com.ToyStory101.instagram.global.exception.CustomException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        session.setAttribute("email", user.getEmail());
        // key value의 값으로 안 주니까 session에서 getAttribute로 가져올 때 email 가져오는 방법이 모호..?

        return ResponseEntity.ok().body(result);

    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);

        if(session != null){ // null이 아니라는건 기존에 세션이 존재했었다는 뜻이므로
            session.invalidate();
        }

        Result result = new Result();
        result.setCode(CODE.SUCCESS);
        result.setMessage("로그아웃 완료");
        result.setData(null);

        return ResponseEntity.ok().body(result);

    }

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody AddUserRequest addUserRequest){
        User user = userService.join(addUserRequest);
//        userService.join(addUserRequest);

//        return ResponseEntity.ok().body("완료");
        Result result = new Result();
        result.setCode(CODE.SUCCESS);
        result.setMessage("회원가입 성공");
        result.setData(user);

        return ResponseEntity.ok().body(result);

    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(HttpServletRequest request){

        HttpSession session = request.getSession(false); //탈퇴시에도 세션 false 해줘야 함.

        if(session==null){
            throw new CustomException(HttpStatus.BAD_REQUEST,"세션이 없습니다. 삭제불가");
        }

        String email = session.getAttribute("email").toString();
        userService.delete(email);
        session.invalidate();

        Result result = new Result();
        result.setCode(CODE.SUCCESS);
        result.setMessage("삭제 성공");
        result.setData(null);

        return ResponseEntity.ok().body(result);

    }


    // TODO: 2024-04-10 email로 변경 필요
    @GetMapping("/findOne/{username}") //여긴 unique 걸린걸로
    public ResponseEntity<?> findOne(@PathVariable String username){
        User user = userService.finduser(username);
        Result result = new Result();
        result.setCode(CODE.SUCCESS);
        result.setMessage("조회 성공");
        result.setData(user);

        return ResponseEntity.ok().body(result);
    }


    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody AddUserRequest addUserRequest, HttpSession session){

        if(session==null){
            throw new CustomException(HttpStatus.BAD_REQUEST,"세션이 없습니다.");
        }
        User user = userService.update(addUserRequest, session.getAttribute("email").toString());

        Result result = new Result();
        result.setCode(CODE.SUCCESS);
        result.setMessage("회원 수정 성공");
        result.setData(user);

        session.setAttribute("email",user.getEmail());

        return ResponseEntity.ok().body(result);

    }

}
