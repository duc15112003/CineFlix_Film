package com.cineflix.vn.model.dto.response;


import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    int id;
    String name;
    String description;
}
