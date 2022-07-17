package org.webApp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.webApp.models.Comment;
import org.webApp.services.CommentService;

import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin(origins="http://localhost:4200")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Comment>> getAllCommentsByPaperId(@PathVariable("id") Long id) {
        List<Comment> comments = commentService.findAllByPaperId(id);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
    @GetMapping("/all/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Comment> getAllCommentsById(@PathVariable("id") Long id) {
        Comment comments = commentService.findCommentById(id);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        Comment newComment = commentService.addComment(comment);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment) {
        Comment updatedComment = commentService.updateComment(comment);
        return new ResponseEntity<>(updatedComment, HttpStatus.CREATED);
    }
}
