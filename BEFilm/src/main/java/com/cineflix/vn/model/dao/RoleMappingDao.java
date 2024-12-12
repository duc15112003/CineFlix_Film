package com.cineflix.vn.model.dao;

import com.cineflix.vn.model.RoleMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleMappingDao extends JpaRepository<RoleMapping, Integer> {
    List<RoleMapping> findByUserUsername(String username);
}
