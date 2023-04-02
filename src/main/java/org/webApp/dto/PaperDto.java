package org.webApp.dto;

import lombok.Builder;
import lombok.Data;
import org.webApp.model.Paper;
import org.webApp.model.Viewer;

import java.util.Date;
@Data
@Builder
public class PaperDto {
    private Long id;
    private String name;
    private String description;
    private String content;
    private Long views;
    private Long likes;
    private Date updated;
    private ViewerDto viewer;

    public static PaperDto fromEntity(Paper paper) {
        return PaperDto.builder()
                .id(paper.getId())
                .name(paper.getName())
                .description(paper.getDescription())
                .content(paper.getContent())
                .views(paper.getViews())
                .likes(paper.getLikes())
                .updated(paper.getUpdated())
                .viewer(ViewerDto.fromEntity(paper.getViewer()))
                .build();
    }

    public static Paper toEntity(PaperDto paperDTO) {
        return Paper.builder()
                .id(paperDTO.getId())
                .name(paperDTO.getName())
                .description(paperDTO.getDescription())
                .content(paperDTO.getContent())
                .views(paperDTO.getViews())
                .likes(paperDTO.getLikes())
                .updated(paperDTO.getUpdated())
                .viewer(ViewerDto.toEntity(paperDTO.getViewer()))
                .build();
    }
}
