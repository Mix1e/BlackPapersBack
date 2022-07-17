package org.webApp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.webApp.models.Paper;
import org.webApp.models.Viewer;
import org.webApp.models.VnLControl;
import org.webApp.services.PaperService;
import org.webApp.services.VnLControlService;

import java.util.List;

@RestController
@RequestMapping("/control")
@CrossOrigin(origins="http://localhost:4200")
public class VnLControlController {

    private final VnLControlService controlService;
    private final PaperService paperService;

    public VnLControlController(VnLControlService controlService, PaperService paperService) {
        this.controlService = controlService;
        this.paperService = paperService;
    }

    @PostMapping("/add")
    public ResponseEntity<VnLControl> addControl(@RequestBody VnLControl control) {
        VnLControl newControl = controlService.addControl(control);
        Paper newPaper = paperService.findPaperById(newControl.getPaper().getId());
        newPaper.incViews();
        paperService.updatePaper(newPaper);
        return new ResponseEntity<>(newControl, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/{nickName}")
    public ResponseEntity<VnLControl> getControl(@PathVariable("id") Long id, @PathVariable("nickName") String nickName) {
        VnLControl control = controlService.findControl(id, nickName);
        return new ResponseEntity<>(control, HttpStatus.OK);
    }

    @GetMapping("/exist/{id}/{nickName}")
    public ResponseEntity<Boolean> getExistByPaperAndViewer(@PathVariable("id") Long id, @PathVariable("nickName") String nickName) {
        return new ResponseEntity<>(controlService.existsControl(id, nickName), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<VnLControl> updateControl(@RequestBody VnLControl control) {
        VnLControl updatedControl = controlService.updateControl(control);
        return new ResponseEntity<>(updatedControl, HttpStatus.OK);
    }

    @PutMapping("/like")
    public ResponseEntity<VnLControl> likesControl(@RequestBody VnLControl control) {
        Paper newPaper = paperService.findPaperById(control.getPaper().getId());
        control.setLiked(!control.isLiked());
        if (control.isLiked())
            newPaper.incLikes();
        else
            newPaper.decLikes();
        paperService.updatePaper(newPaper);
        VnLControl updatedControl = controlService.updateControl(control);
        return new ResponseEntity<>(updatedControl, HttpStatus.OK);
    }
}
