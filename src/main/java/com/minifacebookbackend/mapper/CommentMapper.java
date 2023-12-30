package com.minifacebookbackend.mapper;

import com.minifacebookbackend.domain.model.Comment;
import com.minifacebookbackend.domain.representation.CommentRepresentation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
     List<CommentRepresentation> toCommentRepresentationList(List<Comment> comments);
}
