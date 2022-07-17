package org.webApp.model;

import javax.persistence.*;
import java.util.Date;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "papers")
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

    @ManyToOne
    @JoinColumn(name="viewer_nick", referencedColumnName = "nick_name")
    private Viewer viewer;

    @Column
    @CreationTimestamp
    private Date dateOfUpdate;

}
