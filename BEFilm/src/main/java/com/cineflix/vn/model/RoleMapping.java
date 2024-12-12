package com.cineflix.vn.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "RoleMap")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleMapping {
    @Id
    @Column
    private int id;
    @Column
    private LocalDate CreateAt;
    @Column
    private LocalDate UpdateAt;
    @Column
    private LocalDate DeletedAt;

    // link foregion key
    @ManyToOne
    @JoinColumn
    private Role role;

    @ManyToOne
    @JoinColumn
    private User user;



}
