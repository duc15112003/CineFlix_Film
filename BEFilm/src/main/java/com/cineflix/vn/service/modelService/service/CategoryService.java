package com.cineflix.vn.service.modelService.service;
import com.cineflix.vn.model.Category;
import com.cineflix.vn.model.dto.request.CategoryRequest;
import com.cineflix.vn.model.dto.response.CategoryResponse;
import org.springframework.stereotype.Service;


public interface CategoryService {
    CategoryResponse saveCategory(CategoryRequest category) ;
}
