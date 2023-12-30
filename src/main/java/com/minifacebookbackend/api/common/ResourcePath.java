package com.minifacebookbackend.api.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResourcePath {
  public static final String POST="posts";
  public static final String USER="users";
  public static final String COMMENT="comments";
  public static final String LIKE="likes";
  public static final String UNLIKE="unlikes";
  public static final String AUTHENTIFICATION = "auth";
    public static final String CHAT = "chat";
    public static final String IMAGE = "images";
}
