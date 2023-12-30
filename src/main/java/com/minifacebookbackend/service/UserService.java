package com.minifacebookbackend.service;

import com.minifacebookbackend.domain.command.UserCommand;
import com.minifacebookbackend.domain.model.User;
import com.minifacebookbackend.domain.representation.UserRepresentation;

import java.util.List;

public interface UserService {
    User saveUser(UserCommand userCommand);
    User updateUser(UserCommand userCommand, String userId);
    void deleteUser(String userId);
    List<UserRepresentation> getAll();
    UserRepresentation getById(String userId);
}
