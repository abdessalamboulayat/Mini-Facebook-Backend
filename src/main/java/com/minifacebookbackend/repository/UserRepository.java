package com.minifacebookbackend.repository;

import com.minifacebookbackend.domain.model.Post;
import com.minifacebookbackend.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}
