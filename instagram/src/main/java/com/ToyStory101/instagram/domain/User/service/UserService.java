package com.ToyStory101.instagram.domain.User.service;

import com.ToyStory101.instagram.domain.User.dto.AddUserRequest;
import com.ToyStory101.instagram.domain.User.dto.UserLoginRequest;
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
    public User Login(UserLoginRequest ulr) throws CustomException{
        User user = userRepository.findByEmail(ulr.getEmail())
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "Email이 없습니다."));

        if(!user.getPassword().equals(ulr.getPassword())){ //비번 찾기
            throw new CustomException(HttpStatus.BAD_REQUEST, "비밀번호가 틀렸습니다");
        }

        return user;

    }
    public User join(AddUserRequest aur) throws CustomException{
        if(aur.getEmail() == null | aur.getPassword() == null | aur.getName() == null | aur.getUsername() == null){
            throw new CustomException(HttpStatus.BAD_REQUEST, "정보를 다 입력하세요!");
        } else if (userRepository.existsUserByEmail(aur.getEmail())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "가입 이메일 중복!");
        }

        User user = User.builder()
                .username(aur.getUsername())
                .password(aur.getPassword())
                .phone(aur.getPhone())
                .profileImage(aur.getProfileImage())
                .name(aur.getName())
                .email(aur.getEmail())
                .build();
        userRepository.save(user);
        return user;

    }
}
