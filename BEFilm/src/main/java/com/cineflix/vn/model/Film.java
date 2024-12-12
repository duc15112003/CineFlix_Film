package com.cineflix.vn.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Films")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(columnDefinition = "nvarchar")
    private String original_title;
    @Column(columnDefinition = "nvarchar")
    private String original_language;
    @Column(columnDefinition = "nvarchar")
    private String overview;
    @Column(columnDefinition = "nvarchar")
    private String popularity;
    @Column(columnDefinition = "nvarchar")
    private String poster_path;
    @Column(columnDefinition = "nvarchar")
    private String post_date;
    @Column(columnDefinition = "nvarchar")
    private String video;
    @Column(columnDefinition = "bigint")
    private Long vote_average;
    @Column(columnDefinition = "bigint")
    private Long vote_count;

    @OneToMany(mappedBy = "film")
    List<CategoryMapping> categoryMappings;
    @ManyToOne
    @JoinColumn
    User userid;

    @OneToMany(mappedBy = "idFilm")
    List<episode> episodes;
}

