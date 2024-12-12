package com.cineflix.vn.model.dao;

import com.cineflix.vn.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryFilmDAO extends JpaRepository<Category,Integer> {

}
