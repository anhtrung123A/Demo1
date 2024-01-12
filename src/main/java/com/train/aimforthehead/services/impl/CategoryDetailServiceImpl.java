package com.train.aimforthehead.services.impl;

import com.train.aimforthehead.domain.entities.CategoryDetail;
import com.train.aimforthehead.repositories.CategoryDetailRepository;
import com.train.aimforthehead.services.CategoryDetailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoryDetailServiceImpl implements CategoryDetailService {
    private CategoryDetailRepository categoryDetailRepository;

    public CategoryDetailServiceImpl(CategoryDetailRepository categoryDetailRepository) {
        this.categoryDetailRepository = categoryDetailRepository;
    }

    @Override
    public List<CategoryDetail> findAll() {
            return StreamSupport.stream(categoryDetailRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDetail> findByBookID(int id) {
        return categoryDetailRepository.findAllBybook_id(id);
    }

    @Override
    public List<CategoryDetail> findByCategoryID(int id) {
        return categoryDetailRepository.findAllBycategory_id(id);
    }
}
