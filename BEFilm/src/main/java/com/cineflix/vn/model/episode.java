package com.cineflix.vn.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private Long episode;
    @Column
    private String nameEpisode;
    @Column
    private String overview;
    @Column
    private String link;
    @Column
    private LocalDate CreateAt;
    @Column
    private LocalDate UpdateAt;
    @Column
    private LocalDate DeletedAt;
    @ManyToOne
    @JoinColumn
    private Film idFilm;
}
