package com.cineflix.vn.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Category_Film")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(columnDefinition = "nvarchar")
    private String nameCategory;
    @Column(columnDefinition = "nvarchar")
    private String description;
    @OneToMany(mappedBy = "category")
    List<CategoryMapping> categoryMappings;
}
