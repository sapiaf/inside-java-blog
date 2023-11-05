package dev.sapia.blog.model;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @Lob
    private String content;
    @ManyToOne
    private User author;
    @ManyToOne
    private Category category;

}
