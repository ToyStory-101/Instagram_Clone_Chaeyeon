package com.ToyStory101.instagram.domain.Comment.repository;
import com.ToyStory101.instagram.domain.Comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
