package com.train.aimforthehead.domain.dto;

import com.train.aimforthehead.domain.entities.BookEntity;
import com.train.aimforthehead.domain.entities.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDetailDto {
    private BookEntity book;
    private CategoryEntity category;
}
