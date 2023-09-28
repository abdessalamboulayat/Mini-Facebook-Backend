package com.minifacebookbackend.domain.representation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentRepresentation {
    private String id;
    private String comment;
    private String createdAt;
    private String updatedAt;
    private String userId;
}
