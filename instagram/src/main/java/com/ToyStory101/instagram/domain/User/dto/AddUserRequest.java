package com.ToyStory101.instagram.domain.User.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddUserRequest {
    private String name;
    private String password;
    private String phone;
    private String profileImage;
    private String username;
    private String email;


}
