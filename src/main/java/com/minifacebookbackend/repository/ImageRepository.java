package com.minifacebookbackend.repository;

import com.minifacebookbackend.domain.model.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ImageRepository extends MongoRepository<Image, String> {

    List<Image> findAllByPostId(String postId);
    Image findByUserId(String userId);
    Image findByPostId(String postId);
}
