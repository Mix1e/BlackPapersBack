package org.webApp.dto;

import lombok.Builder;
import lombok.Data;
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

    public static VnLControlDto fromEntity(VnLControl vnLControl) {
        return VnLControlDto.builder()
                .id(vnLControl.getId())
                .paper(PaperDto.fromEntity(vnLControl.getPaper()))
                .viewer(ViewerDto.fromEntity(vnLControl.getViewer()))
                .liked(vnLControl.isLiked())
                .build();
    }

    public static VnLControl toEntity(VnLControlDto vnLControlDto) {
        return VnLControl.builder()
                .id(vnLControlDto.getId())
                .paper(PaperDto.toEntity(vnLControlDto.getPaper()))
                .viewer(ViewerDto.toEntity(vnLControlDto.getViewer()))
                .liked(vnLControlDto.isLiked())
                .build();
    }
}
