package com.cineflix.vn.model.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {
    int id;
    String name;
    String description;

}
