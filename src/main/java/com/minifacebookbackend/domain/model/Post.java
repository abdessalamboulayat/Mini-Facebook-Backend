package com.minifacebookbackend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(value = "post")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Post {
    @Id
    private String id;
    private String createdDate;
    private String updatedDate;
    private String content;
    private String userId;
    @DBRef
    private List<Like> likes;
    @DBRef
    private List<UnLike> unLikes;
    @DBRef
    private List<Comment> comments;
    @DBRef
    private List<Tag> tags;
    @DBRef
    private List<Image> images;
}
