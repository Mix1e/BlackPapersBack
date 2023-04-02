package org.webApp.dto;

import lombok.Builder;
import lombok.Data;
import org.webApp.enums.Role;
import org.webApp.model.Viewer;

@Data
@Builder
public class ViewerDto {
    private String nickName;
    private String description;
    private Role role;

    public static ViewerDto fromEntity(Viewer viewer) {
        return ViewerDto.builder()
                .nickName(viewer.getNickName())
                .description(viewer.getDescription())
                .role(viewer.getRole())
                .build();
    }

    public static Viewer toEntity(ViewerDto viewerDto) {
        return Viewer.builder()
                .nickName(viewerDto.getNickName())
                .description(viewerDto.getDescription())
                .role(viewerDto.getRole())
                .build();
    }
}
