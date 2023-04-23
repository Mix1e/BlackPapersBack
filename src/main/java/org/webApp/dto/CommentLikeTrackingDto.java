package org.webApp.dto;

import lombok.Builder;
import lombok.Data;
import org.webApp.model.CommentLikeTracking;

@Data
@Builder
public class CommentLikeTrackingDto {
    private Long id;
    private boolean liked;
    private CommentDto comment;
    private ViewerDto viewer;

    public CommentLikeTrackingDto(Long id, boolean liked, CommentDto comment, ViewerDto viewer) {
        this.id = id;
        this.liked = liked;
        this.comment = comment;
        this.viewer = viewer;
    }

    public static CommentLikeTrackingDto fromEntity(CommentLikeTracking commentLikeTracking) {
        return CommentLikeTrackingDto.builder()
                .id(commentLikeTracking.getId())
                .liked(commentLikeTracking.isLiked())
                .comment(CommentDto.fromEntity(commentLikeTracking.getComment()))
                .viewer(ViewerDto.fromEntity(commentLikeTracking.getViewer()))
                .build();
    }

    public static CommentLikeTracking toEntity(CommentLikeTrackingDto commentLikeTrackingDto) {
        return new CommentLikeTracking(
                commentLikeTrackingDto.getId(),
                commentLikeTrackingDto.isLiked(),
                CommentDto.toEntity(commentLikeTrackingDto.getComment()),
                ViewerDto.toEntity(commentLikeTrackingDto.getViewer())
        );
    }
}
