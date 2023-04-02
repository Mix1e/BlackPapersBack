package org.webApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webApp.dto.ViewerDto;
import org.webApp.exception.NotFoundException;
import org.webApp.model.Viewer;
import org.webApp.repos.ViewerRepo;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ViewerService {

    private final ViewerRepo viewerRepo;
    private final PaperService paperService;
    private final CommentService commentService;
    private final VnLControlService controlService;

    @Autowired
    public ViewerService(ViewerRepo viewerRepo, PaperService paperService, CommentService commentService, VnLControlService controlService) {
        this.viewerRepo = viewerRepo;
        this.paperService = paperService;
        this.commentService = commentService;
        this.controlService = controlService;
    }

    @Transactional
    public List<ViewerDto> findAllViewers() {
        return viewerRepo.findAll().stream().map(ViewerDto::fromEntity).toList();
    }

    public ViewerDto addViewer(Viewer viewer) {
        return ViewerDto.fromEntity(viewerRepo.save(viewer));
    }

    public ViewerDto findViewerByNickName(String nickName) {
        return ViewerDto.fromEntity((Viewer) viewerRepo.findViewerByNickName(nickName).orElseThrow(() -> new NotFoundException("Такого пользователя не существует")));
    }

    @Transactional
    public void deleteViewerByNickName(String nickName) {
        commentService.deleteAllCommentsByViewerNickName(nickName);
        controlService.deleteAllViewersByNickName(nickName);
        paperService.deleteAllPapersByViewerNickName(nickName);
        viewerRepo.deleteByNickName(nickName);
    }

    @Transactional
    public ViewerDto updateViewer(Viewer viewer) {
        return ViewerDto.fromEntity(viewerRepo.save(viewer));
    }

}
