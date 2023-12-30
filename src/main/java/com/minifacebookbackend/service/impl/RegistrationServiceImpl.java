package com.minifacebookbackend.service.impl;

import com.minifacebookbackend.domain.command.UserCommand;
import com.minifacebookbackend.domain.model.User;
import com.minifacebookbackend.service.ImageService;
import com.minifacebookbackend.service.RegisterService;
import com.minifacebookbackend.service.UserService;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;

@Service
public class RegistrationServiceImpl implements RegisterService {
    @Value("${keycloak.serverUrl}")
    private String serverUrl;
    @Value("${keycloak.realmName}")
    private String realmName;
    @Autowired
    private UserService userService;
    @Autowired
    private ImageService imageService;

    @Override
    public RealmResource getRealmResource() {
        Keycloak kc = KeycloakBuilder.builder().serverUrl(serverUrl).realm("master").username("admin")
                .password("admin").clientId("admin-cli").resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
                .build();
        return kc.realm(realmName);
    }

    @Override
    public UsersResource getUserResource() {
        RealmResource realmResource = getRealmResource();
        return realmResource.users();
    }

    @Override
    public ResponseEntity<?> register(UserCommand userCommand) {
        UsersResource usersResource = getUserResource();
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(userCommand.getEmail());
        userRepresentation.setEmail(userCommand.getEmail());
        userRepresentation.setFirstName(userCommand.getFirstName());
        userRepresentation.setLastName(userCommand.getLastName());
        userRepresentation.setEnabled(true);
        Response response = usersResource.create(userRepresentation);
        if(response.getStatus() == 201){
            // Get user id of new user
            String path = response.getLocation().getPath();
            String userId = path.substring(path.lastIndexOf("/") + 1);
            // set password credential in keycloak
            CredentialRepresentation passwordCredential = new CredentialRepresentation();
            passwordCredential.setTemporary(false);
            passwordCredential.setType(CredentialRepresentation.PASSWORD);
            passwordCredential.setValue(userCommand.getPassword());
            usersResource.get(userId).resetPassword(passwordCredential);
            // save new user in database
            User user = userService.saveUser(userCommand);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else if (response.getStatus() == 409) {
            HashMap<String, Object> result = new HashMap<>();
            result.put("Error:","l'utilisateur existe déja!");
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }else {
            HashMap<String, Object> result = new HashMap<>();
            result.put("Error:","Veuillez réassayer!");
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }

    @Override
    public ResponseEntity<?> register(UserCommand userCommand, MultipartFile file) throws IOException {
        UsersResource usersResource = getUserResource();
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(userCommand.getEmail());
        userRepresentation.setEmail(userCommand.getEmail());
        userRepresentation.setFirstName(userCommand.getFirstName());
        userRepresentation.setLastName(userCommand.getLastName());
        userRepresentation.setEnabled(true);
        Response response = usersResource.create(userRepresentation);
        if(response.getStatus() == 201){
            // Get user id of new user
            String path = response.getLocation().getPath();
            String userId = path.substring(path.lastIndexOf("/") + 1);
            System.out.println("USER ID "+userId);
            // set password credential in keycloak
            CredentialRepresentation passwordCredential = new CredentialRepresentation();
            passwordCredential.setTemporary(false);
            passwordCredential.setType(CredentialRepresentation.PASSWORD);
            passwordCredential.setValue(userCommand.getPassword());
            usersResource.get(userId).resetPassword(passwordCredential);
            // save new user in database
            User user = userService.saveUser(userCommand);
            user.setImage(imageService.saveProfileImage(file, user.getId()));
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else if (response.getStatus() == 409) {
            HashMap<String, Object> result = new HashMap<>();
            result.put("Error:","l'utilisateur existe déja!");
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }else {
            HashMap<String, Object> result = new HashMap<>();
            result.put("Error:","Veuillez réassayer!");
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }
}
