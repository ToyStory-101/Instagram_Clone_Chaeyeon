package com.ToyStory101.instagram.domain.User.service;

import com.ToyStory101.instagram.domain.User.dto.AddUserRequest;
import com.ToyStory101.instagram.domain.User.dto.UserLoginRequest;
import com.ToyStory101.instagram.domain.User.entity.User;
import com.ToyStory101.instagram.global.exception.CustomException;
import com.ToyStory101.instagram.domain.User.repository.UserRepository;
import jakarta.transaction.Transactional;
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

    @Transactional //update시에도 필요
    public User update(AddUserRequest aur, String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->new CustomException(HttpStatus.BAD_REQUEST,"사용자가 일치하지 않습니다."));

        //더티체킹 repository에 update가 없음
        if(aur.getName()!=null){
            user.setName(aur.getName());
        }
        if(aur.getUsername()!=null){
            user.setUsername(aur.getUsername());
        }
        if(aur.getPassword()!=null){
            user.setPassword(aur.getPassword());
        }
        if(aur.getPhone()!=null){
            user.setPhone(aur.getPhone());
        }
        if(aur.getProfileImage()!=null){
            user.setProfileImage(aur.getProfileImage());
        }
        if(aur.getEmail()!=null){
            user.setEmail(aur.getEmail());
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

    public User finduser(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow( ()-> new CustomException(HttpStatus.BAD_REQUEST,"사용자 정보가 없습니다."));
        return user;
    }

    @Transactional // 삭제시 Transaction 필수
    public void delete(String email){
        userRepository.deleteUserByEmail(email);
    }

}
