package org.webApp.service;

import org.springframework.stereotype.Service;
import org.webApp.dto.VnLControlDto;
import org.webApp.exception.NotFoundException;
import org.webApp.model.VnLControl;
import org.webApp.repos.VnLControlRepo;

import javax.transaction.Transactional;

@Service
public class VnLControlService {

    private final VnLControlRepo controlRepo;

    public VnLControlService(VnLControlRepo controlRepo) {
        this.controlRepo = controlRepo;
    }

    @Transactional
    public VnLControlDto addControl(VnLControlDto controlDto) {
        return VnLControlDto.fromEntity(controlRepo.save(VnLControlDto.toEntity(controlDto)));
    }

    @Transactional
    public VnLControlDto updateControl(VnLControlDto controlDto) {
        return VnLControlDto.fromEntity(controlRepo.save(VnLControlDto.toEntity(controlDto)));
    }

    @Transactional
    public VnLControlDto findControl(Long id, String nickName) {
        return VnLControlDto.fromEntity(
                (VnLControl) controlRepo.findVnLControlByPaperIdAndViewerNickName(id, nickName).orElseThrow(() -> new NotFoundException("Такого пользователя не существует"))
                );
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
