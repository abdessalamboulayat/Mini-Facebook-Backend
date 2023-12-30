package com.minifacebookbackend.service.impl;

import com.minifacebookbackend.domain.command.LikeCommand;
import com.minifacebookbackend.domain.command.UnLikeCommand;
import com.minifacebookbackend.domain.model.Like;
import com.minifacebookbackend.domain.model.UnLike;
import com.minifacebookbackend.domain.representation.LikeRepresentation;
import com.minifacebookbackend.domain.representation.UnLikeRepresentation;
import com.minifacebookbackend.mapper.UnLikeMapper;
import com.minifacebookbackend.repository.UnLikeRepository;
import com.minifacebookbackend.service.UnlikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class UnLikeServiceImpl implements UnlikeService {

    private final UnLikeRepository unLikeRepository;
    private final UnLikeMapper unLikeMapper;

    @Override
    public UnLike saveUnLike(UnLikeCommand unlike) {
        if(unlike == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"like to save is null");
        }
        UnLike unlikeToSave = new UnLike();
        unlikeToSave.setUserId(unlike.getUserId());
        unlikeToSave.setPostId(unlike.getPostId());
        return unLikeRepository.save(unlikeToSave) ;
    }

    @Override
    public void deleteUnLike(String unlikeId) {
        UnLike like = unLikeRepository.findById(unlikeId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "like id is not valid"));
        unLikeRepository.delete(like);
    }

    @Override
    public void deleteUnLikes(List<UnLike> unlikes){
        unLikeRepository.deleteAll(unlikes);
    }

    @Override
    public List<UnLikeRepresentation> getUnLikesByPostId(String postId) {
        return unLikeMapper.toUnLikeRepresentationList(unLikeRepository.findAllByPostId(postId));
    }



}
