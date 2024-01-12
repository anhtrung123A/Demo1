package com.train.aimforthehead.domain.dto;

import com.train.aimforthehead.domain.entities.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseDto {
    private int id;
    private Date date;
    private CustomerEntity customer;
}
