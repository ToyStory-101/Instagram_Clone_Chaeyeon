package com.ToyStory101.instagram.domain.User.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor //유저 객체를 만들 때 new user() set 머머머로 채워넣을 수 있다.
@AllArgsConstructor // 모든게 다 있는 상태로 new user()를 만들 수 있다. -> Builder 쓰려면 꼭 있어야 함.
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true,nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    private String phone;
    private String profileImage;

    @CreationTimestamp
    private Timestamp createDate;

}
