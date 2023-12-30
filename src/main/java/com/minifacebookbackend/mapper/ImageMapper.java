package com.minifacebookbackend.mapper;

import com.minifacebookbackend.domain.model.Image;
import com.minifacebookbackend.domain.representation.ImageRepresentation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    List<ImageRepresentation> toImageRepresentationList(List<Image> images);
    ImageRepresentation toImageRepresentation(Image image);

}
