package com.ToyStory101.instagram.domain.User.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {
    private String email;
    private String name;
    private String password;
    private String phone;
    private String profileImage;
    private String username;


//    public User toEntity(){
//        return User.builder()
//                .email(email)
//                .name(name)
//                .password(password)
//                .phone(phone)
//                .profileImage(profileImage)
//                .username(username)
//                .build();
//    }
}
