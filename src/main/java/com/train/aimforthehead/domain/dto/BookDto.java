package com.train.aimforthehead.domain.dto;

import com.train.aimforthehead.domain.entities.AuthorEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
    private int id;
    private String title;
    private AuthorEntity author;
    private String image;
    private double price;
    private String description;
}

