package com.minifacebookbackend.mapper;

import com.minifacebookbackend.domain.model.User;
import com.minifacebookbackend.domain.representation.UserRepresentation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserRepresentation toUserRepresentation(User user);
  User toUser(UserRepresentation userRepresentation);
  List<UserRepresentation> toUserRepresentationList(List<User> users);

}
