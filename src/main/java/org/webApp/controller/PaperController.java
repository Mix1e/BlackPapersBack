package org.webApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.webApp.dto.PaperDto;
import org.webApp.model.Paper;
import org.webApp.service.PaperService;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins="http://localhost:4200")
public class PaperController {

    private final PaperService paperService;

    public PaperController(PaperService paperService) {
        this.paperService = paperService;
    }

    @GetMapping("/blogs")
    public ResponseEntity<List<PaperDto>> getAllPapers() {
        return new ResponseEntity<>(paperService.findAllPapers(), HttpStatus.OK);
    }

    @GetMapping("/blogs/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<PaperDto> getPaperById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(paperService.findPaperById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<PaperDto> addPaper(@RequestBody Paper paper) {
        return new ResponseEntity<>(paperService.addPaper(paper), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<PaperDto> updatePaper(@RequestBody PaperDto paperDto) {
        return new ResponseEntity<>(paperService.updatePaper(paperDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> deletePaper(@PathVariable("id") Long id) {
        paperService.deletePaper(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
