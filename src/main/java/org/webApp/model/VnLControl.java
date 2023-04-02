package org.webApp.model;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
@Builder
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

    public VnLControl(Long id, Paper paper, Viewer viewer, boolean liked) {
        this.id = id;
        this.paper = paper;
        this.viewer = viewer;
        this.liked = liked;
    }
}
