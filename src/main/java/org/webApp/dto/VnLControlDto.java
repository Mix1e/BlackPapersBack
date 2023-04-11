package org.webApp.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.webApp.model.Comment;
import org.webApp.model.Paper;
import org.webApp.model.Viewer;
import org.webApp.model.VnLControl;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@Builder
public class VnLControlDto {
    private Long id;
    private PaperDto paper;
    private ViewerDto viewer;
    private boolean liked;

    public VnLControlDto(Long id, PaperDto paper, ViewerDto viewer, boolean liked) {
        this.id = id;
        this.paper = paper;
        this.viewer = viewer;
        this.liked = liked;
    }

    public static VnLControlDto fromEntity(VnLControl vnLControl) {
        return VnLControlDto.builder()
                .id(vnLControl.getId())
                .liked(vnLControl.isLiked())
                .paper(PaperDto.fromEntity(vnLControl.getPaper()))
                .viewer(ViewerDto.fromEntity(vnLControl.getViewer()))
                .build();
    }

    public static VnLControl toEntity(VnLControlDto vnLControlDto) {
        return new VnLControl(
                vnLControlDto.getId(),
                vnLControlDto.isLiked(),
                PaperDto.toEntity(vnLControlDto.getPaper()),
                ViewerDto.toEntity(vnLControlDto.getViewer())
        );
    }
}
