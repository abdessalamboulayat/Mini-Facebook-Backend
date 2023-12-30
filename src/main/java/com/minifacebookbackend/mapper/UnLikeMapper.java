package com.minifacebookbackend.mapper;

import com.minifacebookbackend.domain.model.Like;
import com.minifacebookbackend.domain.model.UnLike;
import com.minifacebookbackend.domain.representation.UnLikeRepresentation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UnLikeMapper {
    UnLikeRepresentation toUnLikeRepresentation(UnLike unlike);
    List<UnLikeRepresentation> toUnLikeRepresentationList(List<UnLike> unlikes);
}
