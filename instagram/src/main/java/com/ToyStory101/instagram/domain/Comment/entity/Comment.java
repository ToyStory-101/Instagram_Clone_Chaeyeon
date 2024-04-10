package com.ToyStory101.instagram.domain.Comment.entity;

import com.ToyStory101.instagram.domain.Image.entity.Image;
import com.ToyStory101.instagram.domain.User.entity.User;
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

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    @ManyToOne
    private Image image;

    @ManyToOne
    private User user;

    @CreationTimestamp
    private Timestamp createDate;
}
