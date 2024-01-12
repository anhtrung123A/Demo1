package com.train.aimforthehead.mappers.impl;

import com.train.aimforthehead.domain.dto.CategoryDetailDto;
import com.train.aimforthehead.domain.entities.CategoryDetail;
import com.train.aimforthehead.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryDetailMapperImpl implements Mapper<CategoryDetail, CategoryDetailDto> {
    private ModelMapper modelMapper;

    public CategoryDetailMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDetail mapFrom(CategoryDetailDto categoryDetailDto) {
        return modelMapper.map(categoryDetailDto, CategoryDetail.class);
    }
    @Override
    public CategoryDetailDto mapTo(CategoryDetail categoryDetail) {
        return modelMapper.map(categoryDetail, CategoryDetailDto.class);
    }
}
