package org.webApp.dto;

import lombok.Builder;
import lombok.Data;
import org.webApp.model.Comment;

import java.util.Date;

@Data
@Builder
public class CommentDto {
    private Long id;
    private String content;
    private Long likes;
    private Date dateOfUpdate;
    private PaperDto paper;
    private ViewerDto viewer;

    public static CommentDto fromEntity(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .likes(comment.getLikes())
                .dateOfUpdate(comment.getDateOfUpdate())
                .paper(PaperDto.fromEntity(comment.getPaper()))
                .viewer(ViewerDto.fromEntity(comment.getViewer()))
                .build();
    }

    public static Comment toEntity(CommentDto commentDto) {
        return Comment.builder()
                .id(commentDto.getId())
                .content(commentDto.getContent())
                .likes(commentDto.getLikes())
                .dateOfUpdate(commentDto.getDateOfUpdate())
                .paper(PaperDto.toEntity(commentDto.getPaper()))
                .viewer(ViewerDto.toEntity(commentDto.getViewer()))
                .build();
    }
}
