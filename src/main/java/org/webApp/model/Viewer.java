package org.webApp.model;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.webApp.enums.Role;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
@Builder
@Table(name = "viewers")
public class Viewer {

    @Id
    @Column(name = "nick_name")
    private String nickName;

    @Column
    private String password;

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    public Viewer(String nickName, String password, String description, Role role) {
        this.nickName = nickName;
        this.password = password;
        this.description = description;
        this.role = role;
    }
}

