package com.minifacebookbackend.domain.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageCommand {
 private String id;
 private String url;
 private MultipartFile file;
 private String postId;
 private String userId;
 @JsonProperty("isNew")
 private boolean isNew;
}
