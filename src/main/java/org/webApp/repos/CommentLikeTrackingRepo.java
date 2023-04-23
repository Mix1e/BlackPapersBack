package org.webApp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.webApp.model.CommentLikeTracking;
import org.webApp.model.VnLControl;

import java.util.Optional;

@Repository
public interface CommentLikeTrackingRepo extends JpaRepository<CommentLikeTracking, Long> {

    Boolean existsCommentLikeTrackingByCommentIdAndViewerNickName(Long id, String nickname);

    Optional<Object> findCommentLikeTrackingByCommentIdAndViewerNickName(Long id, String nickName);

    void deleteAllCommentLikeTrackingByViewerNickName(String nickName);

    void deleteCommentLikeTrackingByCommentId(Long id);
    void deleteAllCommentLikeTrackingByCommentId(Long id);
}
