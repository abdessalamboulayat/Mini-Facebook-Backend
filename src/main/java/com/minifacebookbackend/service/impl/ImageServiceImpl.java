package com.minifacebookbackend.service.impl;

import com.minifacebookbackend.domain.command.ImageCommand;
import com.minifacebookbackend.domain.model.Image;
import com.minifacebookbackend.domain.representation.ImageRepresentation;
import com.minifacebookbackend.mapper.ImageMapper;
import com.minifacebookbackend.repository.ImageRepository;
import com.minifacebookbackend.service.ImageService;
import com.minifacebookbackend.util.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;
    @Override
    public Image saveImage(ImageCommand imageCommand, String postId) {
        if(imageCommand == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "image to save is null");
        }
        Image imageToSave = new Image();
        imageToSave.setUrl(imageCommand.getUrl());
        if(!postId.isEmpty())
            imageToSave.setPostId(postId);

        return imageRepository.save(imageToSave);
    }
    @Override
    public List<Image> saveImages(MultipartFile file,String postId) throws IOException {
        List<Image> images = new ArrayList<>();
        imageRepository.save(Image.builder()
                .postId(postId)
                .imageBytes(ImageUtils.compressImage(file.getBytes()))
                .url("").build());
        return images;
    }
    @Override
    public ImageRepresentation updateProfileImage(MultipartFile file, String userId) throws IOException {
            Image imageToUpdate = imageRepository.findByUserId(userId);
            if(imageToUpdate != null && imageToUpdate.getUserId().equals(userId)){
                imageToUpdate.setImageBytes(file.getBytes());
                imageToUpdate.setUrl(file.getOriginalFilename());
                System.out.println("iamge exist so  updating :      : "+file.getOriginalFilename());
                return imageMapper.toImageRepresentation(imageRepository.save(imageToUpdate));
            }
            else{
                // let's save new image
                System.out.println("iamge new so  creating :      : "+file.getOriginalFilename());
               return imageMapper.toImageRepresentation(saveProfileImage(file, userId));
            }
    }

    @Override
    public void deleteImage(String imageId) {
        Image image = imageRepository.findById(imageId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "image id is not valid"));
        imageRepository.delete(image);
    }
    @Override
    public void deleteImages(List<Image> images){
        imageRepository.deleteAll(images);
    }
    @Override
    public void updateImages(MultipartFile file, String postId) {
        Image image = imageRepository.findByPostId(postId);
        try {
            image.setImageBytes(ImageUtils.compressImage(file.getBytes()));
            imageRepository.save(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<ImageRepresentation> getImagesByPostId(String postId){
        List<Image> images = imageRepository.findAllByPostId(postId);
        for(Image image:images){
            image.setImageBytes(ImageUtils.decompressImage(image.getImageBytes()));
        }
        return imageMapper.toImageRepresentationList(images);
    }
    @Override
    public ImageRepresentation getImage(String imageId){
        return imageMapper.toImageRepresentation(imageRepository.findById(imageId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "image id is not valid")));
    }

    @Override
    public Image saveProfileImage(MultipartFile file, String userId) throws IOException {
        System.out.println("user id image : "+userId);
        Image image = new Image();
        if(file != null) {
            System.out.println("image != null "+file.getOriginalFilename());
            image.setUserId(userId);
            image.setImageBytes(file.getBytes());
            return imageRepository.save(image);
        }
        return image;
    }

    @Override
    public ImageRepresentation getImageByUserId(String userId) {
        Image image = imageRepository.findByUserId(userId);
        if(image != null){
            return getImage(image.getId());
        }
        return null;
    }

}
