package com.minifacebookbackend.api;

import com.minifacebookbackend.api.common.ResourcePath;
import com.minifacebookbackend.domain.command.LikeCommand;
import com.minifacebookbackend.domain.command.UnLikeCommand;
import com.minifacebookbackend.domain.model.Like;
import com.minifacebookbackend.domain.model.UnLike;
import com.minifacebookbackend.service.LikeService;
import com.minifacebookbackend.service.UnlikeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ResourcePath.UNLIKE)
@AllArgsConstructor
@Slf4j
public class UnLikeResource {
    private final UnlikeService unlikeService;

    @PostMapping
    public ResponseEntity<UnLike> saveLike(@RequestBody UnLikeCommand unlikeCommand){
        return ResponseEntity.ok(unlikeService.saveUnLike(unlikeCommand));
    }

    @DeleteMapping("/{unlikeId}")
    public ResponseEntity<String> deleteLike(@PathVariable String unlikeId){
        unlikeService.deleteUnLike(unlikeId);
        return ResponseEntity.ok("Unlike deleted successfully");
    }
}
