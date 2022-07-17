package org.webApp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.webApp.models.Viewer;
import org.webApp.services.CommentService;
import org.webApp.services.PaperService;
import org.webApp.services.ViewerService;
import org.webApp.services.VnLControlService;

import java.util.List;

@RestController
@RequestMapping("/viewers")
@CrossOrigin(origins="http://localhost:4200")
public class ViewerController {

    private final ViewerService viewerService;
    private final PaperService paperService;
    private final CommentService commentService;
    private final VnLControlService controlService;

    public ViewerController(ViewerService viewerService, PaperService paperService, CommentService commentService, VnLControlService controlService) {
        this.viewerService = viewerService;
        this.paperService = paperService;
        this.commentService = commentService;
        this.controlService = controlService;
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
        controlService.deleteAllViewersByNickName(nickName);
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
