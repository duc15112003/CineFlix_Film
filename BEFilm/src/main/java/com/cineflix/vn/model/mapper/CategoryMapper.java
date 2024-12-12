package com.cineflix.vn.model.mapper;

import com.cineflix.vn.model.Category;
import com.cineflix.vn.model.dto.request.CategoryRequest;
import com.cineflix.vn.model.dto.response.CategoryResponse;
import org.mapstruct.factory.*;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    Category toCategoryResponse(CategoryResponse categoryResponse);
    CategoryResponse toCategory(Category category);
    Category toCategorya(CategoryRequest categoryRequest);
    CategoryRequest toCategoryReq(Category category);

}
