package com.train.aimforthehead.controllers.rest;

import com.train.aimforthehead.domain.dto.CategoryDto;
import com.train.aimforthehead.domain.entities.CategoryEntity;
import com.train.aimforthehead.mappers.Mapper;
import com.train.aimforthehead.repositories.CategoryRepository;
import com.train.aimforthehead.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CategoryController {
    private CategoryService categoryService;
    private Mapper<CategoryEntity, CategoryDto> categoryMapper;
    private CategoryRepository categoryRepository;

    public CategoryController(CategoryService categoryService, Mapper<CategoryEntity, CategoryDto> categoryMapper, CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @CrossOrigin
    @GetMapping(path = "/categories")
    public List<CategoryDto> listCategories()
    {
        List<CategoryEntity> categoryEntityList = categoryService.findAll();
        return categoryEntityList.stream().map(categoryMapper::mapTo).collect(Collectors.toList());
    }
    @GetMapping(path = "/categories/{category_name}")
    public CategoryDto getCategoryByName(@PathVariable("category_name") String name)
    {
        CategoryEntity categoryEntity = categoryService.findByCategoryName(name);
        return categoryMapper.mapTo(categoryEntity);
    }
}
