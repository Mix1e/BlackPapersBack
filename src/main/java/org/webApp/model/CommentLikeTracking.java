package org.webApp.model;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
@Table(name="comment_tracker")
public class CommentLikeTracking {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean liked;

    @ManyToOne
    @JoinColumn(name="comment_id", referencedColumnName = "id")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name="viewer_nick", referencedColumnName = "nick_name")
    private Viewer viewer;

    public CommentLikeTracking(Long id, boolean liked, Comment comment, Viewer viewer) {
        this.id = id;
        this.liked = liked;
        this.comment = comment;
        this.viewer = viewer;
    }
}
