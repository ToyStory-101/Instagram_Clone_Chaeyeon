package com.ToyStory101.instagram.domain.Image.repository;

import com.ToyStory101.instagram.domain.Image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Integer> {
}
