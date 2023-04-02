package org.webApp.model;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@RequiredArgsConstructor
@Table(name="comment")
@Builder
public class Comment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition="TEXT")
    @Lob
    private String content;

    @Column
    private Long likes;

    @Column
    @CreationTimestamp
    private Date dateOfUpdate;

    @ManyToOne
    @JoinColumn(name="paper_id", referencedColumnName = "id")
    private Paper paper;

    @ManyToOne
    @JoinColumn(name="viewer_nick", referencedColumnName = "nick_name")
    private Viewer viewer;

    public Comment(Long id, String content, Long likes, Date dateOfUpdate, Paper paper, Viewer viewer) {
        this.id = id;
        this.content = content;
        this.likes = likes;
        this.dateOfUpdate = dateOfUpdate;
        this.paper = paper;
        this.viewer = viewer;
    }
}
