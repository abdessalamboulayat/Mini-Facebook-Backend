package com.minifacebookbackend.mapper;

import com.minifacebookbackend.domain.model.Like;
import com.minifacebookbackend.domain.representation.LikeRepresentation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LikeMapper {
    LikeRepresentation toLikeRepresentation(Like like);
    List<LikeRepresentation> toLikeRepresentationList(List<Like> likes);
}
