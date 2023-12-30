package com.minifacebookbackend.domain.representation;

import com.minifacebookbackend.domain.model.Comment;
import com.minifacebookbackend.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostRepresentation {
    private String id;
    private UserRepresentation user;
    private String createdDate;
    private String updatedDate;
    private String content;
    private List<TagRepresentation> tags;
    private List<CommentRepresentation> comments;
    private List<ImageRepresentation> images;
    private List<LikeRepresentation> likes;
    private List<UnLikeRepresentation> unLikes;
}
