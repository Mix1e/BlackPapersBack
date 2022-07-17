package org.webApp.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.webApp.enums.Role;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
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
}

