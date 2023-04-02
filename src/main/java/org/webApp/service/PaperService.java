package org.webApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webApp.dto.PaperDto;
import org.webApp.exception.NotFoundException;
import org.webApp.model.Paper;
import org.webApp.repos.PaperRepo;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PaperService {
    private final PaperRepo paperRepo;
    private final CommentService commentService;
    private final VnLControlService controlService;

    @Autowired
    public PaperService(PaperRepo paperRepo, CommentService commentService, VnLControlService controlService) {
        this.paperRepo = paperRepo;
        this.commentService = commentService;
        this.controlService = controlService;
    }

    @Transactional
    public PaperDto addPaper(Paper paper) {
        return PaperDto.fromEntity(paperRepo.save(paper));
    }

    @Transactional
    public List<PaperDto> findAllPapers() {
        return paperRepo.findAll().stream().map(PaperDto::fromEntity).toList();
    }

    @Transactional
    public PaperDto updatePaper(Paper paper) {
        return PaperDto.fromEntity(paperRepo.save(paper));
    }
    @Transactional
    public void deletePaper(Long id) {
        commentService.deleteAllCommentsByPaperId(id);
        controlService.deleteAllPapersById(id);
        paperRepo.deletePaperById(id);
    }

    @Transactional
    public PaperDto findPaperById(Long id) {
        return PaperDto.fromEntity(paperRepo.findPaperById(id).orElseThrow(() -> new NotFoundException("Такой статьи не существует")));
    }
    @Transactional
    public void deleteAllPapersByViewerNickName(String nickName) {
        paperRepo.deleteAllPapersByViewerNickName(nickName);
    }
}
