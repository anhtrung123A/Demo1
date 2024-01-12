package com.train.aimforthehead.controllers.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseInfo {
    private int p_id;
    private int count;
    private double total;
}
