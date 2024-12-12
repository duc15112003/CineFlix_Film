package com.cineflix.vn.service.modelService.serviceiplm;

import com.cineflix.vn.model.Category;
import com.cineflix.vn.model.dao.CategoryFilmDAO;
import com.cineflix.vn.model.dto.request.CategoryRequest;
import com.cineflix.vn.model.dto.response.CategoryResponse;
import com.cineflix.vn.model.mapper.CategoryMapper;
import com.cineflix.vn.service.modelService.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceIplm implements CategoryService {
    @Autowired
    private CategoryFilmDAO categoryFilmDAO;
    @Override
    public CategoryResponse saveCategory(CategoryRequest category) {
        Category categorys = CategoryMapper.INSTANCE.toCategorya(category);
        CategoryResponse categoryResponse = CategoryMapper.INSTANCE.toCategory(categoryFilmDAO.save(categorys));
        return categoryResponse;
    }
}
