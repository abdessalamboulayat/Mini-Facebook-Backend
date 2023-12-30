package com.minifacebookbackend.mapper;

import com.minifacebookbackend.domain.model.Tag;
import com.minifacebookbackend.domain.representation.TagRepresentation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {

    List<TagRepresentation> toTagRepresentationList(List<Tag> tags);

}
