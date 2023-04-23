package org.webApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.webApp.dto.ViewerDto;
import org.webApp.model.Viewer;
import org.webApp.service.CommentService;
import org.webApp.service.PaperService;
import org.webApp.service.ViewerService;
import org.webApp.service.VnLControlService;

import java.util.List;

@RestController
@RequestMapping("/api/viewers")
@CrossOrigin
public class ViewerController {

    private final ViewerService viewerService;

    public ViewerController(ViewerService viewerService) {
        this.viewerService = viewerService;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<ViewerDto>> getAllViewers() {
        return new ResponseEntity<>(viewerService.findAllViewers(), HttpStatus.OK);
    }
    @GetMapping("/{nickName}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ViewerDto> getViewerByNickName(@PathVariable("nickName") String nickName) {
        return new ResponseEntity<>(viewerService.findViewerByNickName(nickName), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ViewerDto> addViewer(@RequestBody Viewer viewer) {
        return new ResponseEntity<>(viewerService.addViewer(viewer), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{nickName}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteViewer(@PathVariable("nickName") String nickName) {
        viewerService.deleteViewerByNickName(nickName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ViewerDto> updateViewer(@RequestBody Viewer viewer) {
        return new ResponseEntity<>(viewerService.updateViewer(viewer), HttpStatus.CREATED);
    }
}
