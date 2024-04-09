package com.ToyStory101.instagram.domain.Likes.entity;

import com.ToyStory101.instagram.domain.User.entity.User;
import com.ToyStory101.instagram.domain.Image.entity.Image;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Image image;

    @CreationTimestamp
    private Timestamp createDate;


}
