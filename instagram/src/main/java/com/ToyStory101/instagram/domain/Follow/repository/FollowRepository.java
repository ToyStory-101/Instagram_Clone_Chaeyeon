package com.ToyStory101.instagram.domain.Follow.repository;

import com.ToyStory101.instagram.domain.Follow.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Integer> {
}
