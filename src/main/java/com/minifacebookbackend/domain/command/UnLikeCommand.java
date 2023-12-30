package com.minifacebookbackend.domain.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UnLikeCommand {
    private String id;
    private String postId;
    private String userId;
}
