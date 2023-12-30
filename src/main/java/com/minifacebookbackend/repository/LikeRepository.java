package com.minifacebookbackend.repository;

import com.minifacebookbackend.domain.model.Like;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LikeRepository extends MongoRepository<Like, String> {

    List<Like> findAllByPostId(String postId);

}
