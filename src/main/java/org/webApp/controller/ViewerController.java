package org.webApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.webApp.model.Paper;
import org.webApp.model.Viewer;
import org.webApp.service.CommentService;
import org.webApp.service.PaperService;
import org.webApp.service.ViewerService;

import java.util.List;

@RestController
@RequestMapping("/viewers")
@CrossOrigin(origins="http://localhost:4200")
public class ViewerController {

    private final ViewerService viewerService;
    private final PaperService paperService;
    private final CommentService commentService;

    public ViewerController(ViewerService viewerService, PaperService paperService, CommentService commentService) {
        this.viewerService = viewerService;
        this.paperService = paperService;
        this.commentService = commentService;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Viewer>> getAllViewers() {
        List<Viewer> viewers = viewerService.findAllViewers();
        return new ResponseEntity<>(viewers, HttpStatus.OK);
    }
    @GetMapping("/{nickName}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Viewer> getViewerByNickName(@PathVariable("nickName") String nickName) {
        Viewer viewer = viewerService.findViewerByNickName(nickName);
        return new ResponseEntity<>(viewer, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Viewer> addViewer(@RequestBody Viewer viewer) {
        Viewer newViewer = viewerService.addViewer(viewer);
        return new ResponseEntity<>(newViewer, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{nickName}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteViewer(@PathVariable("nickName") String nickName) {
        commentService.deleteAllCommentsByViewerNickName(nickName);
        paperService.deleteAllPapersByViewerNickName(nickName);
        viewerService.deleteViewerByNickName(nickName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Viewer> updateViewer(@RequestBody Viewer viewer) {
        Viewer updatedViewer = viewerService.updateViewer(viewer);
        return new ResponseEntity<>(updatedViewer, HttpStatus.CREATED);
    }
}
