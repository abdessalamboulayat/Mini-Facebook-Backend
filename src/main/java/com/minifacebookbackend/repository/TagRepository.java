package com.minifacebookbackend.repository;

import com.minifacebookbackend.domain.model.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TagRepository extends MongoRepository<Tag, String> {

    List<Tag> findAllByPostId(String postId);

}
