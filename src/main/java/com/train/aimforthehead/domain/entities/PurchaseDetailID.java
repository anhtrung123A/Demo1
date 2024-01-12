package com.train.aimforthehead.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class PurchaseDetailID {
    private BookEntity book;
    private PurchaseEntity purchase;
}
