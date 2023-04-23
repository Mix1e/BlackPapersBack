package org.webApp.service;

import org.springframework.stereotype.Service;
import org.webApp.dto.CommentLikeTrackingDto;
import org.webApp.exception.NotFoundException;
import org.webApp.model.CommentLikeTracking;
import org.webApp.repos.CommentLikeTrackingRepo;

import javax.transaction.Transactional;

@Service
public class CommentLikeTrackingService {

    private final CommentLikeTrackingRepo trackingRepo;

    public CommentLikeTrackingService(CommentLikeTrackingRepo trackingRepo) {
        this.trackingRepo = trackingRepo;
    }

    @Transactional
    public CommentLikeTrackingDto addControl(CommentLikeTrackingDto controlDto) {
        return CommentLikeTrackingDto.fromEntity(trackingRepo.save(CommentLikeTrackingDto.toEntity(controlDto)));
    }

    @Transactional
    public CommentLikeTrackingDto updateControl(CommentLikeTrackingDto controlDto) {
        return CommentLikeTrackingDto.fromEntity(trackingRepo.save(CommentLikeTrackingDto.toEntity(controlDto)));
    }

    @Transactional
    public CommentLikeTrackingDto findControl(Long id, String nickName) {
        return CommentLikeTrackingDto.fromEntity(
                (CommentLikeTracking) trackingRepo.findCommentLikeTrackingByCommentIdAndViewerNickName(id, nickName).orElseThrow(() -> new NotFoundException("Такого пользователя не существует"))
                );
    }

    @Transactional
    public boolean existsControl(Long id, String nickName) {
        return trackingRepo.existsCommentLikeTrackingByCommentIdAndViewerNickName(id, nickName);
    }

    @Transactional
    public void deleteAllById(Long id) {
        trackingRepo.deleteCommentLikeTrackingByCommentId(id);
    }

    @Transactional
    public void deleteAllByCommentId(Long id) {
        trackingRepo.deleteAllCommentLikeTrackingByCommentId(id);
    }

    @Transactional
    public void deleteAllViewersByNickName(String nickName) {
        trackingRepo.deleteAllCommentLikeTrackingByViewerNickName(nickName);
    }
}
