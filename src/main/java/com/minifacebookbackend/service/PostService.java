package com.minifacebookbackend.service;

import com.minifacebookbackend.domain.command.PostCommand;
import com.minifacebookbackend.domain.criterias.PostCriteria;
import com.minifacebookbackend.domain.model.Post;
import com.minifacebookbackend.domain.representation.PostRepresentation;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {
    Post savePost(String userId, String content, MultipartFile file) throws IOException;
    PostRepresentation updatePost(PostCommand postCommand,MultipartFile file,String postId);
    void deletePost(String postId);
    PostRepresentation getPostById(String postId);
    List<PostRepresentation> getAll(PostCriteria postCriteria);

    List<PostRepresentation> findPostsByUserId(String userId);
}
