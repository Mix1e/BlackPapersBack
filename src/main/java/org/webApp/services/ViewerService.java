package org.webApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webApp.exceptions.NotFoundException;
import org.webApp.models.Viewer;
import org.webApp.repos.ViewerRepo;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ViewerService {

    private final ViewerRepo viewerRepo;

    @Autowired
    public ViewerService(ViewerRepo viewerRepo) {
        this.viewerRepo = viewerRepo;
    }

    @Transactional
    public List<Viewer> findAllViewers() {
        return viewerRepo.findAll();
    }

    public Viewer addViewer(Viewer viewer) {
        return viewerRepo.save(viewer);
    }

    public Viewer findViewerByNickName(String nickName) {
        return (Viewer) viewerRepo.findViewerByNickName(nickName).orElseThrow(() -> new NotFoundException("Такого пользователя не существует"));
    }

    @Transactional
    public void deleteViewerByNickName(String nickName) {
        viewerRepo.deleteByNickName(nickName);
    }

    @Transactional
    public Viewer updateViewer(Viewer viewer) {
        return viewerRepo.save(viewer);
    }

}
