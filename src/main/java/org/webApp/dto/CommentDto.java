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
        return new Comment(
                commentDto.getId(),
                commentDto.getContent(),
                commentDto.getLikes(),
                commentDto.getDateOfUpdate(),
                PaperDto.toEntity(commentDto.getPaper()),
                ViewerDto.toEntity(commentDto.getViewer())
        );
    }
}
