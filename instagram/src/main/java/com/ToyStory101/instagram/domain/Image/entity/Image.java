package com.ToyStory101.instagram.domain.Image.entity;
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
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String location;
    private String caption;
    private String imageUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="userId") //칼럼명 지정
    //칼럼명 지정 안 하면 user_Id가 기본 칼럼명으로 지정됨.
    private User user; //타입은 User 오프젝트의 PK 타입.


    @CreationTimestamp
    private Timestamp createDate;


}
