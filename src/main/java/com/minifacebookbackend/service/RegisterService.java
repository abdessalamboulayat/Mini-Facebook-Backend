package com.minifacebookbackend.service;

import com.minifacebookbackend.domain.command.UserCommand;
import com.minifacebookbackend.domain.model.User;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.core.Response;
import java.io.IOException;

public interface RegisterService {
    RealmResource getRealmResource();
    UsersResource getUserResource();
    ResponseEntity<?> register(UserCommand userCommand);
    ResponseEntity<?> register(UserCommand userCommand, MultipartFile file) throws IOException;
}
