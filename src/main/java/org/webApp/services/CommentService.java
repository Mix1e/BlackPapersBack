package org.webApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webApp.models.Comment;
import org.webApp.repos.CommentRepo;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepo commentRepo;

    @Autowired
    public CommentService(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }
    @Transactional
    public List<Comment> findAllByPaperId(Long id) {
        return commentRepo.findAllCommentsByPaperId(id);
    }
    @Transactional
    public Comment findCommentById(Long id) {
        return commentRepo.findCommentById(id);
    }
    @Transactional
    public Comment addComment(Comment comment) {
        return commentRepo.save(comment);
    }

    @Transactional
    public void deleteComment(Long id) {
        commentRepo.deleteById(id);
    }
    @Transactional
    public Comment updateComment(Comment comment) {
        return commentRepo.save(comment);
    }

    @Transactional
    public void deleteAllCommentsByPaperId(Long id) {
        commentRepo.deleteAllCommentsByPaperId(id);
    }

    @Transactional
    public void deleteAllCommentsByViewerNickName(String nickName) {
        commentRepo.deleteAllCommentsByViewerNickName(nickName);
    }
}
