package org.webApp.repos;

import org.springframework.stereotype.Repository;
import org.webApp.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {

    List<Comment> findAllCommentsByPaperId(Long id);

    Comment findCommentById(Long id);

    void deleteAllCommentsByPaperId(Long id);

    void deleteAllCommentsByViewerNickName(String nickName);
}
