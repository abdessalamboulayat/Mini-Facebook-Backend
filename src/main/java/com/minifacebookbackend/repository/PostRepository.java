package com.minifacebookbackend.repository;

import com.minifacebookbackend.domain.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {


    @Query("{ 'content' : { $regex: ?0, $options: 'i' } }")
    List<Post> findAllByContentIgnoreCase(String content);

    List<Post> findPostsByUserId(String userId);
}
