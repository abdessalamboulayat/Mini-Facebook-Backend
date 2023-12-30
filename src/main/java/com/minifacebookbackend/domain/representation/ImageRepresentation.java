package com.minifacebookbackend.domain.representation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageRepresentation {
    private String id;
    private String url;
    private byte[] imageBytes;
    private String userId;
}
