package com.codejourney.pimber.controller;

import com.codejourney.pimber.dto.CommentDto;
import com.codejourney.pimber.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> findAll(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(commentService.findAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.findById(id));
    }

    @PostMapping("/add-comment")
    public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto commentDto) {
        CommentDto responseComment = commentService.postComment(commentDto);
        URI location = URI.create(String.format("/comments/%s", responseComment.getId()));

        return ResponseEntity.created(location).body(responseComment);
    }
}
