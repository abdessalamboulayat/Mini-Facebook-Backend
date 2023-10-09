package com.minifacebookbackend.repository;

import com.minifacebookbackend.domain.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findAllByPostId(String postId);

}
