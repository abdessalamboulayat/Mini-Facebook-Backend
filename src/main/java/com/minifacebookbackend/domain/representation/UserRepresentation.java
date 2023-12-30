package com.minifacebookbackend.domain.representation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRepresentation {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String location;
    private String biography;
    private String createdAt;
    private ImageRepresentation profile;
    private List<PostRepresentation> posts;
}
