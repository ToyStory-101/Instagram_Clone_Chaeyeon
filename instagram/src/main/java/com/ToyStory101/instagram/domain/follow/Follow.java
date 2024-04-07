package com.ToyStory101.instagram.domain.follow;

import com.ToyStory101.instagram.domain.user.User;
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
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="fromUserId")
    private User fromUser;

    @ManyToOne
    @JoinColumn(name="toUserId")
    private User toUser;

    @CreationTimestamp
    private Timestamp createDate;

}
