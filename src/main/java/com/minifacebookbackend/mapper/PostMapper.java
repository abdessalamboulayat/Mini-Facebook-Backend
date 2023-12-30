package com.minifacebookbackend.mapper;

import com.minifacebookbackend.domain.model.Post;
import com.minifacebookbackend.domain.representation.PostRepresentation;
import com.minifacebookbackend.domain.representation.UserRepresentation;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface PostMapper {
    PostRepresentation toPostRepresentation(Post post);
    List<PostRepresentation> toPostRepresentationList(List<Post> posts);
}
