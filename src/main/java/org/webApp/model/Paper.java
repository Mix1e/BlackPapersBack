package org.webApp.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
@Data
@Table(name = "paper")
public class Paper {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column(columnDefinition="TEXT")
    @Lob
    private String description;
    @Column(columnDefinition="TEXT")
    @Lob
    private String content;
    @Column
    private Long views;
    @Column
    private Long likes;

    @Column
    @CreationTimestamp
    private Date updated;

    @ManyToOne
    @JoinColumn(name="viewer_nick", referencedColumnName = "nick_name")
    private Viewer viewer;

    public Paper() {}

    public Paper(Long id, String name, String description, String content, Long views, Long likes, Date updated, Viewer viewer) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.content = content;
        this.views = views;
        this.likes = likes;
        this.updated = updated;
        this.viewer = viewer;
    }
}
