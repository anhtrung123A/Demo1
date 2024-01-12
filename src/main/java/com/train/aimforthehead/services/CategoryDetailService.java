package com.train.aimforthehead.services;

import com.train.aimforthehead.domain.entities.CategoryDetail;

import java.util.List;

public interface CategoryDetailService {
    List<CategoryDetail> findAll();

    List<CategoryDetail> findByBookID(int id);

    List<CategoryDetail> findByCategoryID(int id);
}
