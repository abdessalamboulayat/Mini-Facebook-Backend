package com.minifacebookbackend.api;

import com.minifacebookbackend.api.common.ResourcePath;
import com.minifacebookbackend.domain.representation.ImageRepresentation;
import com.minifacebookbackend.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(ResourcePath.IMAGE)
@RequiredArgsConstructor
@Slf4j
public class ImageResource {

    private final ImageService imageService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<ImageRepresentation> getImageByUserId(@PathVariable String userId){
        return ResponseEntity.ok(imageService.getImageByUserId(userId));
    }

    @PutMapping(value = "/user/{userId}", consumes = {"multipart/form-data"})
    public ResponseEntity<ImageRepresentation> updateUserProfile(@RequestPart(value = "file") MultipartFile file,@PathVariable("userId") String userId) throws IOException {
        log.info("am in the ressource for the update image Profile");
        System.out.println("receiving the request ...");
        System.out.println("file : : : : => "+ file.getName());
        return ResponseEntity.ok(imageService.updateProfileImage(file,userId));
    }
}
