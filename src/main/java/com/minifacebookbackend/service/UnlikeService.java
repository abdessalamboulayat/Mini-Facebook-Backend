package com.minifacebookbackend.service;




import com.minifacebookbackend.domain.command.UnLikeCommand;
import com.minifacebookbackend.domain.model.UnLike;
import com.minifacebookbackend.domain.representation.UnLikeRepresentation;

import java.util.List;

public interface UnlikeService {
    UnLike saveUnLike(UnLikeCommand like);
    void deleteUnLike(String unlikeId);
    void deleteUnLikes(List<UnLike> unlikes);
    List<UnLikeRepresentation> getUnLikesByPostId(String postId);
}
