package com.minifacebookbackend.api;

import com.minifacebookbackend.api.common.ResourcePath;
import com.minifacebookbackend.domain.command.ImageCommand;
import com.minifacebookbackend.domain.command.UserCommand;
import com.minifacebookbackend.domain.model.User;
import com.minifacebookbackend.domain.representation.ImageRepresentation;
import com.minifacebookbackend.domain.representation.UserRepresentation;
import com.minifacebookbackend.service.ImageService;
import com.minifacebookbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ResourcePath.USER)
@RequiredArgsConstructor
@Slf4j
public class UserResource {

    private final UserService userService;
    private final ImageService imageService;
    @GetMapping
    public ResponseEntity<List<UserRepresentation>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserRepresentation> getById(@PathVariable String userId){
        log.info("Loading user with id {}", userId);
        return ResponseEntity.ok(userService.getById(userId));
    }
    @GetMapping("/image/{userId}")
    public ResponseEntity<ImageRepresentation> getImageByUserId(@PathVariable String userId){
        return ResponseEntity.ok(imageService.getImageByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody UserCommand userCommand) {
        return ResponseEntity.ok(userService.saveUser(userCommand));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody UserCommand userCommand, @PathVariable String userId) {
        return ResponseEntity.ok(userService.updateUser(userCommand, userId));
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("utilisateur supprimé avec succès");
    }

}
