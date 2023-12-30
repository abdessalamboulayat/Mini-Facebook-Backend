package com.minifacebookbackend.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.codec.multipart.Part;

import java.util.List;

@Document(value = "user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
  @Id
  private String id;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String location;
  private String biography;
  private String createdAt;
  @DBRef
  private List<Post> posts;
  @DBRef
  private List<Comment> comments;
  @DBRef
  private List<Like> likes;
  @DBRef
  private Image image;
}
