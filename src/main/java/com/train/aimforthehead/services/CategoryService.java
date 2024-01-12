package com.train.aimforthehead.services;

import com.train.aimforthehead.domain.entities.CategoryEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryEntity> findAll();

    CategoryEntity findByCategoryName(String name);
}
