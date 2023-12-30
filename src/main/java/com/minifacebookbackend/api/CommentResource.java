package com.minifacebookbackend.api;

import com.minifacebookbackend.api.common.ResourcePath;
import com.minifacebookbackend.domain.command.CommentCommand;
import com.minifacebookbackend.domain.model.Comment;
import com.minifacebookbackend.domain.representation.CommentRepresentation;
import com.minifacebookbackend.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ResourcePath.COMMENT)
@AllArgsConstructor
@Slf4j
public class CommentResource {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> saveComment(@RequestBody CommentCommand commentCommand){
        return ResponseEntity.ok(commentService.saveComment(commentCommand));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Comment> updateComment(@RequestBody CommentCommand commentCommand,@PathVariable String commentId){
        return ResponseEntity.ok(commentService.updateComment(commentCommand));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable String commentId){
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("comment deleted successfully");
    }





}
