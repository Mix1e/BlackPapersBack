package org.webApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.webApp.model.Paper;
import org.webApp.repos.PaperRepo;
import org.webApp.service.CommentService;
import org.webApp.service.PaperService;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins="http://localhost:4200")
public class PaperController {

    private final PaperService paperService;

    private final CommentService commentService;

    public PaperController(PaperService paperService, CommentService commentService) {
        this.paperService = paperService;
        this.commentService = commentService;
    }

    @GetMapping("/blogs")
    public ResponseEntity<List<Paper>> getAllPapers() {
        List<Paper> papers = paperService.findAllPapers();
        return new ResponseEntity<>(papers, HttpStatus.OK);
    }

    @GetMapping("/blogs/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Paper> getPaperById(@PathVariable("id") Long id) {
        Paper paper = paperService.findPaperById(id);
        return new ResponseEntity<>(paper, HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Paper> addPaper(@RequestBody Paper paper) {
        Paper newPaper = paperService.addPaper(paper);
        return new ResponseEntity<>(newPaper, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Paper> updatePaper(@RequestBody Paper paper) {
        Paper updatedPaper = paperService.updatePaper(paper);
        return new ResponseEntity<>(updatedPaper, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> deletePaper(@PathVariable("id") Long id) {
        commentService.deleteAllCommentsByPaperId(id);
        paperService.deletePaper(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
