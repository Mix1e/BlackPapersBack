package org.webApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webApp.dto.*;
import org.webApp.service.CommentLikeTrackingService;
import org.webApp.service.CommentService;
import org.webApp.service.ViewerService;

@RestController
@RequestMapping("/api/comments/tracking")
@CrossOrigin
public class CommentLikeTrackingController {
    private final CommentLikeTrackingService trackingService;
    private final CommentService commentService;
    private final ViewerService viewerService;

    public CommentLikeTrackingController(CommentLikeTrackingService trackingService, CommentService commentService, ViewerService viewerService) {
        this.trackingService = trackingService;
        this.commentService = commentService;
        this.viewerService = viewerService;
    }

    @PostMapping("/add")
    public ResponseEntity<CommentLikeTrackingDto> addControl(@RequestBody CreateTrackingRequest createTrackingRequest) {
        CommentDto commentDto = this.commentService.findCommentById(createTrackingRequest.getCommentId());
        ViewerDto viewerDto = viewerService.findViewerByNickName(createTrackingRequest.getViewerName());
        CommentLikeTrackingDto newControl = new CommentLikeTrackingDto(null, false, commentDto, viewerDto);
        return new ResponseEntity<>(trackingService.addControl(newControl), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/{nickName}")
    public ResponseEntity<CommentLikeTrackingDto> getControl(@PathVariable("id") Long id, @PathVariable("nickName") String nickName) {
        return new ResponseEntity<>(
                trackingService.findControl(id, nickName),
                HttpStatus.OK
        );
    }

    @GetMapping("/exist/{id}/{nickName}")
    public ResponseEntity<Boolean> getExistByCommentAndViewer(@PathVariable("id") Long id, @PathVariable("nickName") String nickName) {
        return new ResponseEntity<>(trackingService.existsControl(id, nickName), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<CommentLikeTrackingDto> updateControl(@RequestBody CommentLikeTrackingDto controlDto) {
        return new ResponseEntity<>(trackingService.updateControl(controlDto), HttpStatus.OK);
    }

    @PutMapping("/like")
    public ResponseEntity<CommentLikeTrackingDto> likesControl(@RequestBody LikeRequest likeRequest) {
        CommentLikeTrackingDto controlDto = trackingService.findControl(likeRequest.getId(), likeRequest.getNickname());
        CommentDto commentDto = commentService.findCommentById(likeRequest.getId());

        controlDto.setLiked(!controlDto.isLiked());
        if (controlDto.isLiked())
            commentDto.incLikes();
        else
            commentDto.decLikes();
        commentService.updateComment(CommentDto.toEntity(commentDto));
        return new ResponseEntity<>(trackingService.updateControl(controlDto), HttpStatus.OK);
    }
}
