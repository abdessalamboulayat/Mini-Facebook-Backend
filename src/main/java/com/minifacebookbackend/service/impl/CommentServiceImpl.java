package com.minifacebookbackend.service.impl;

import com.minifacebookbackend.domain.command.CommentCommand;
import com.minifacebookbackend.domain.model.Comment;
import com.minifacebookbackend.domain.representation.CommentRepresentation;
import com.minifacebookbackend.mapper.CommentMapper;
import com.minifacebookbackend.repository.CommentRepository;
import com.minifacebookbackend.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final UserServiceImpl userService;
    @Override
    public Comment saveComment(CommentCommand comment){
        if(comment == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "comment to save is null");
        }
        Comment commentToSave = new Comment();
        commentToSave.setComment(comment.getComment());
        commentToSave.setCreatedAt(LocalDateTime.now().toString());
        commentToSave.setPostId(comment.getPostId());
        commentToSave.setUserId(comment.getUserId());
        return commentRepository.save(commentToSave);
    }

    @Override
    public Comment updateComment(CommentCommand commmentCommand) {
             Comment commentToUpdate= commentRepository.findById(commmentCommand.getId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "comment id is not valid"));
             commentToUpdate.setComment(commmentCommand.getComment());
             commentToUpdate.setUpdatedAt(LocalDateTime.now().toString());
             commentToUpdate.setPostId(commmentCommand.getPostId());
             commentToUpdate.setUserId(commmentCommand.getUserId());
             return commentRepository.save(commentToUpdate);
    }

    @Override
    public void deleteComment(String commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "comment id is not valid"));
        commentRepository.delete(comment);
    }
    @Override
    public void deleteComments(List<Comment> comments){
        System.out.println("comment service : delete comments");
        commentRepository.deleteAll(comments);
    }

    @Override
    public List<CommentRepresentation> getCommentsByPostId(String postId) {
       List<Comment> commentList = commentRepository.findAllByPostId(postId);
       List<CommentRepresentation> commentRepresentationList = commentMapper.toCommentRepresentationList(commentList);
       for(CommentRepresentation commentRepresentation:commentRepresentationList){
           for(Comment comment:commentList){
               if(commentRepresentation.getId().equals(comment.getId())){
                   commentRepresentation.setUser(userService.getById(comment.getUserId()));
               }
           }
       }
        return commentRepresentationList;
    }

}
