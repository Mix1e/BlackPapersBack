package org.webApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webApp.dto.*;
import org.webApp.service.PaperService;
import org.webApp.service.ViewerService;
import org.webApp.service.VnLControlService;

@RestController
@RequestMapping("/control")
@CrossOrigin(origins = "http://localhost:4200")
public class VnLControlController {

    private final VnLControlService controlService;
    private final PaperService paperService;
    private final ViewerService viewerService;

    public VnLControlController(VnLControlService controlService, PaperService paperService, ViewerService viewerService) {
        this.controlService = controlService;
        this.paperService = paperService;
        this.viewerService = viewerService;
    }

    @PostMapping("/add")
    public ResponseEntity<VnLControlDto> addControl(@RequestBody CreateControlRequest createControlRequest) {
        PaperDto paperDto = paperService.findPaperById(createControlRequest.getPaperId());
        ViewerDto viewerDto = viewerService.findViewerByNickName(createControlRequest.getViewerName());
        paperDto.incViews();
        paperService.updatePaper(paperDto);
        VnLControlDto newControl = new VnLControlDto(null, paperDto, viewerDto, createControlRequest.getLiked());
        return new ResponseEntity<>(controlService.addControl(newControl), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/{nickName}")
    public ResponseEntity<VnLControlDto> getControl(@PathVariable("id") Long id, @PathVariable("nickName") String nickName) {
        return new ResponseEntity<>(
                controlService.findControl(id, nickName),
                HttpStatus.OK
        );
    }

    @GetMapping("/exist/{id}/{nickName}")
    public ResponseEntity<Boolean> getExistByPaperAndViewer(@PathVariable("id") Long id, @PathVariable("nickName") String nickName) {
        return new ResponseEntity<>(controlService.existsControl(id, nickName), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<VnLControlDto> updateControl(@RequestBody VnLControlDto controlDto) {
        return new ResponseEntity<>(controlService.updateControl(controlDto), HttpStatus.OK);
    }

    @PutMapping("/like")
    public ResponseEntity<VnLControlDto> likesControl(@RequestBody LikeRequest likeRequest) {
        VnLControlDto controlDto = controlService.findControl(likeRequest.getPaperId(), likeRequest.getNickname());
        PaperDto paperDto = paperService.findPaperById(likeRequest.getPaperId());

        controlDto.setLiked(!controlDto.isLiked());
        if (controlDto.isLiked())
            paperDto.incLikes();
        else
            paperDto.decLikes();
        paperService.updatePaper(paperDto);
        return new ResponseEntity<>(controlService.updateControl(controlDto), HttpStatus.OK);
    }
}
