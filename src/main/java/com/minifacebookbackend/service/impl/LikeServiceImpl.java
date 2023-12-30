package com.minifacebookbackend.service.impl;

import com.minifacebookbackend.domain.command.LikeCommand;
import com.minifacebookbackend.domain.model.Like;
import com.minifacebookbackend.domain.representation.LikeRepresentation;
import com.minifacebookbackend.mapper.LikeMapper;
import com.minifacebookbackend.repository.LikeRepository;
import com.minifacebookbackend.service.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;
    private final LikeMapper likeMapper;
    @Override
    public Like saveLike(LikeCommand like) {
        if(like == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"like to save is null");
        }
        Like likeToSave = new Like();
        likeToSave.setUserId(like.getUserId());
        likeToSave.setPostId(like.getPostId());
        return likeRepository.save(likeToSave) ;
    }

    @Override
    public LikeRepresentation getLikeById(String likeId) {
        return likeMapper.toLikeRepresentation(likeRepository.findById(likeId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "like id is not valid")));
    }


    @Override
    public void deleteLike(String likeId) {
        Like like = likeRepository.findById(likeId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "like id is not valid"));
        likeRepository.delete(like);
    }
    @Override
    public void deleteLikes(List<Like> likes){
        System.out.println("like service : delete");
        likeRepository.deleteAll(likes);
    }

    @Override
    public List<LikeRepresentation> getLikesByPostId(String postId) {
        return likeMapper.toLikeRepresentationList(likeRepository.findAllByPostId(postId));
    }

}
