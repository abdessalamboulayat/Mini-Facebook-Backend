package com.minifacebookbackend.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minifacebookbackend.api.common.ResourcePath;
import com.minifacebookbackend.domain.command.PostCommand;
import com.minifacebookbackend.domain.criterias.PostCriteria;
import com.minifacebookbackend.domain.model.Post;
import com.minifacebookbackend.domain.representation.PostRepresentation;
import com.minifacebookbackend.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(ResourcePath.POST)
@RequiredArgsConstructor
@Slf4j
public class PostResource {

    private final PostService postService;

    @GetMapping("/{postId}")
    public ResponseEntity<PostRepresentation> getPostDetails(@PathVariable String postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @GetMapping
    public ResponseEntity<List<PostRepresentation>> getAllPosts(@RequestParam Map<String, String> requestBody){
        PostCriteria postCriteria = new PostCriteria();
        postCriteria.setContent(requestBody.get("content"));
        log.info("postCriteria: {}", postCriteria);
        return ResponseEntity.ok(postService.getAll(postCriteria));
    }

    @PostMapping(consumes = {"multipart/form-data", "application/json"})
    public ResponseEntity<Post> savePost(@RequestPart("userId") String userId,
                                         @RequestPart(value = "tags",required = false)  String tags,
                                         @RequestPart("content") String content,
                                         @RequestPart(value = "file",required = false) MultipartFile file) throws IOException {
        log.info("savePost: {}",file);
        return ResponseEntity.ok(postService.savePost(userId, content, file));
    }

    @PutMapping(value = "/{postId}", consumes = {"multipart/form-data", MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PostRepresentation> updatePost(@RequestPart String post, @PathVariable String postId,
                                                         @RequestPart(value = "file",required = false) MultipartFile file) throws JsonProcessingException {
        PostCommand postCommand = new PostCommand();
            System.out.println("post:"+post);
            ObjectMapper objectMapper = new ObjectMapper();
            postCommand = objectMapper.readValue(post, PostCommand.class);
            log.info("modifier le post : {} ", postCommand);
        return ResponseEntity.ok(postService.updatePost(postCommand, file,postId));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable String postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok("Post deleted successfully");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostRepresentation>> getPostsUsers(@PathVariable String userId) {
        return ResponseEntity.ok(postService.findPostsByUserId(userId));
    }

}
