package com.ToyStory101.instagram.domain.Likes.repository;

import com.ToyStory101.instagram.domain.Likes.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Integer> {



}
