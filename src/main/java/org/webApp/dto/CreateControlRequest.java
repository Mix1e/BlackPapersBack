package org.webApp.dto;

public class CreateControlRequest {
    private Long paperId;
    private String viewerName;
    private Boolean liked;

    public CreateControlRequest(Long paperId, String viewerName, Boolean liked) {
        this.paperId = paperId;
        this.viewerName = viewerName;
        this.liked = liked;
    }

    public Long getPaperId() {
        return paperId;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
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
