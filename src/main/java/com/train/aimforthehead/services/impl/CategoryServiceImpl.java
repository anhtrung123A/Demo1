package com.train.aimforthehead.services.impl;

import com.train.aimforthehead.domain.entities.CategoryEntity;
import com.train.aimforthehead.repositories.CategoryRepository;
import com.train.aimforthehead.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryEntity> findAll() {
        return StreamSupport.stream(categoryRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public CategoryEntity findByCategoryName(String name) {
        return categoryRepository.findByname(name);
    }
}
