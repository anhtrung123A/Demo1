package com.train.aimforthehead.domain.dto;

import com.train.aimforthehead.domain.entities.BookEntity;
import com.train.aimforthehead.domain.entities.PurchaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseDetailDto {
    private BookEntity book;
    private PurchaseEntity purchase;
    private int quantity;
}
