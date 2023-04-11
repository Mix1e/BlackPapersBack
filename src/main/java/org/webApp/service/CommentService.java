package org.webApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webApp.dto.CommentDto;
import org.webApp.model.Comment;
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
    public List<CommentDto> findAllByPaperId(Long id) {
        return commentRepo.findAllCommentsByPaperId(id).stream().map(CommentDto::fromEntity).toList();
    }
    @Transactional
    public CommentDto findCommentById(Long id) {
        return CommentDto.fromEntity(commentRepo.findCommentById(id));
    }
    @Transactional
    public CommentDto addComment(Comment comment) {
        comment.setLikes(Long.valueOf( 0));
        return CommentDto.fromEntity(commentRepo.save(comment));
    }

    @Transactional
    public void deleteComment(Long id) {
        commentRepo.deleteById(id);
    }
    @Transactional
    public CommentDto updateComment(Comment comment) {
        return CommentDto.fromEntity(commentRepo.save(comment));
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
