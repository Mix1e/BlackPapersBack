package org.webApp.model;
import lombok.Builder;
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

    @Column
    private boolean liked;

    @ManyToOne
    @JoinColumn(name="paper_id", referencedColumnName = "id")
    private Paper paper;

    @ManyToOne
    @JoinColumn(name="viewer_nick", referencedColumnName = "nick_name")
    private Viewer viewer;

    public VnLControl(Long id, boolean liked, Paper paper, Viewer viewer) {
        this.id = id;
        this.liked = liked;
        this.paper = paper;
        this.viewer = viewer;
    }
}
