package com.train.aimforthehead.controllers.rest;

import com.train.aimforthehead.domain.dto.CategoryDetailDto;
import com.train.aimforthehead.domain.entities.CategoryDetail;
import com.train.aimforthehead.mappers.Mapper;
import com.train.aimforthehead.repositories.CategoryDetailRepository;
import com.train.aimforthehead.services.CategoryDetailService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CategoryDetailController {
    private CategoryDetailRepository categoryDetailRepository;
    private CategoryDetailService categoryDetailService;
    private Mapper<CategoryDetail, CategoryDetailDto> categoryDetailMapper;

    public CategoryDetailController(CategoryDetailRepository categoryDetailRepository, CategoryDetailService categoryDetailService, Mapper<CategoryDetail, CategoryDetailDto> categoryDetailMapper) {
        this.categoryDetailRepository = categoryDetailRepository;
        this.categoryDetailService = categoryDetailService;
        this.categoryDetailMapper = categoryDetailMapper;
    }
    @CrossOrigin
    @GetMapping(path = "/category_details")
    public List<CategoryDetailDto> listCategoryDetails()
    {
        List<CategoryDetail> categoryDetailList = categoryDetailService.findAll();
        return categoryDetailList.stream().map(categoryDetailMapper::mapTo).collect(Collectors.toList());
    }
    @CrossOrigin
    @GetMapping(path = "/category_details/by-books/{book_id}")
    public List<CategoryDetailDto> findCDByBookID(@PathVariable("book_id") int id)
    {
        List<CategoryDetail> categoryDetailList = categoryDetailService.findByBookID(id);
        return categoryDetailList.stream().map(categoryDetailMapper::mapTo).collect(Collectors.toList());
    }
    @CrossOrigin
    @GetMapping(path = "/category_details/by-categories/{category_id}")
    public List<CategoryDetailDto> findCDByCategoryID(@PathVariable("category_id") int id)
    {
        List<CategoryDetail> categoryDetailList = categoryDetailService.findByCategoryID(id);
        return categoryDetailList.stream().map(categoryDetailMapper::mapTo).collect(Collectors.toList());
    }
}
