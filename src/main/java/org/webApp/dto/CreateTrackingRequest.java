package org.webApp.dto;

public class CreateTrackingRequest {
    private Long commentId;
    private String viewerName;
    private Boolean liked;

    public CreateTrackingRequest(Long commentId, String viewerName, Boolean liked) {
        this.commentId = commentId;
        this.viewerName = viewerName;
        this.liked = liked;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getViewerName() {
        return viewerName;
    }

    public void setViewerName(String viewerName) {
        this.viewerName = viewerName;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }
}
