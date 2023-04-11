package org.webApp.dto;

public class LikeRequest {
    private Long paperId;
    private String nickName;

    public LikeRequest(Long paperId, String nickName) {
        this.paperId = paperId;
        this.nickName = nickName;
    }

    public Long getPaperId() {
        return paperId;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }

    public String getNickname() {
        return nickName;
    }

    public void setNickname(String nickName) {
        this.nickName = nickName;
    }
}
