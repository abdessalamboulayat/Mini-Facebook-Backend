package com.minifacebookbackend.domain.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCommand {
    private String content;
    private String userId;
    private List<TagCommand> tags;
    private List<ImageCommand> images;
}
