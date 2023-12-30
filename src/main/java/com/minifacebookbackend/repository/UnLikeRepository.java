package com.minifacebookbackend.repository;

import com.minifacebookbackend.domain.model.UnLike;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UnLikeRepository extends MongoRepository<UnLike, String> {

    List<UnLike> findAllByPostId(String postId);

}
