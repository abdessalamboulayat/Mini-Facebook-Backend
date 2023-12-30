package com.minifacebookbackend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "tag")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Tag {
    @Id
    private String id;
    private String content;
    private String postId;
}
