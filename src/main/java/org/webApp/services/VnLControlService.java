package org.webApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webApp.exceptions.NotFoundException;
import org.webApp.models.Comment;
import org.webApp.models.Viewer;
import org.webApp.models.VnLControl;
import org.webApp.repos.VnLControlRepo;

import javax.transaction.Transactional;

@Service
public class VnLControlService {

    private final VnLControlRepo controlRepo;

    @Autowired
    public VnLControlService(VnLControlRepo controlRepo) {
        this.controlRepo = controlRepo;
    }

    @Transactional
    public VnLControl addControl(VnLControl control) {
        return controlRepo.save(control);
    }

    @Transactional
    public VnLControl updateControl(VnLControl control) {
        return controlRepo.save(control);
    }

    @Transactional
    public VnLControl findControl(Long id, String nickName) {
        return (VnLControl) controlRepo.findVnLControlByPaperIdAndViewerNickName(id, nickName).orElseThrow(() -> new NotFoundException("Такого пользователя не существует"));
    }

    @Transactional
    public boolean existsControl(Long id, String nickName) {
        return controlRepo.existsVnLControlByPaperIdAndViewerNickName(id, nickName);
    }

    @Transactional
    public void deleteAllPapersById(Long id) {
        controlRepo.deleteAllVnLControlsByPaperId(id);
    }

    @Transactional
    public void deleteAllViewersByNickName(String nickName) {
        controlRepo.deleteAllVnLControlsByViewerNickName(nickName);
    }
}
