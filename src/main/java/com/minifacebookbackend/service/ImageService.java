package com.minifacebookbackend.service;

import com.minifacebookbackend.domain.command.ImageCommand;
import com.minifacebookbackend.domain.model.Image;
import com.minifacebookbackend.domain.representation.ImageRepresentation;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    Image saveImage(ImageCommand imageCommand, String postId);
    ImageRepresentation updateProfileImage(MultipartFile file, String userId) throws IOException;
    void deleteImage(String imageId);
    List<Image> saveImages(MultipartFile file,String postId) throws IOException;
    void deleteImages(List<Image> images);
    void updateImages(MultipartFile file,String postId) throws IOException;
    List<ImageRepresentation> getImagesByPostId(String postId);
    ImageRepresentation getImage(String imageId);
    Image saveProfileImage(MultipartFile file, String userId) throws IOException;
    ImageRepresentation getImageByUserId(String userId);
}
