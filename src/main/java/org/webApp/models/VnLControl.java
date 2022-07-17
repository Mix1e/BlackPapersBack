package org.webApp.models;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
public class VnLControl {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="paper_id", referencedColumnName = "id")
    private Paper paper;

    @ManyToOne
    @JoinColumn(name="viewer_nick", referencedColumnName = "nick_name")
    private Viewer viewer;

    @Column
    private boolean liked;
}
