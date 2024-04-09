package com.ToyStory101.instagram.domain.User.service;

import com.ToyStory101.instagram.domain.User.entity.User;
import com.ToyStory101.instagram.global.exception.CustomException;
import com.ToyStory101.instagram.domain.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Service
@RequestMapping("/api/v1/member")
public class UserService {

    private final UserRepository userRepository;

//    public User Login(String username, String password){
//        Optional<User> user = userRepository.findByUsername(username);
//        if (user.isEmpty()){ //유저 못 찾음
//            return null;
//        }
//        else if (user.get().getPassword().equals(password)){
//            return user.get(); //성립
//        }
//        return null; //유저 O 비번 X
//    }


        public User Login(String username, String password) throws CustomException{
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "사용자가 바보입니다"));

            if(!user.getPassword().equals(password)){
                throw new CustomException(HttpStatus.BAD_REQUEST, "비밀번호가 틀렸습니다");
            }
            return user;

        }
        public void signup(String username, String password) throws CustomException{
            if(username.isEmpty() | password.isEmpty()){
                throw new CustomException(HttpStatus.BAD_REQUEST, "정보를 다 입력하세요!");
            }
            else if(userRepository.existsUserByUsername(username)){
                throw new CustomException(HttpStatus.BAD_REQUEST, "아이디 중복!");
            }

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);

            userRepository.save(user);
        }
}
