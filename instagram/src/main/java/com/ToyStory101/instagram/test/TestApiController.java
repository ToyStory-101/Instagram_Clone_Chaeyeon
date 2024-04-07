package com.ToyStory101.instagram.test;

import com.ToyStory101.instagram.domain.follow.FollowRepository;
import com.ToyStory101.instagram.domain.image.Image;
import com.ToyStory101.instagram.domain.image.ImageRepository;
import com.ToyStory101.instagram.domain.like.LikesRepository;
import com.ToyStory101.instagram.domain.user.User;
import com.ToyStory101.instagram.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestApiController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageRepository imageRepository;
    @GetMapping("/test/api/join")
    public User join(){
        User user = User.builder()
                .name("최주호")
                .password("1234")
                .phone("0102222")
                .build();

        User userEntity = userRepository.save(user);
        return userEntity;
    }

    @GetMapping("/test/api/image")
    public String image(){
        User userEntity = userRepository.findById(1).get();

        Image image = Image.builder()
                .location("다낭")
                .caption("설명")
                .user(userEntity)
                .build();

        Image imageEntity = imageRepository.save(image);

        return "Image Insert 잘 됨";
    }

    @GetMapping("/test/api/image/list")
    public List<Image> imageList(){
        return imageRepository.findAll();
    }



}
