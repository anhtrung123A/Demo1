package com.train.aimforthehead.mappers.impl;

import com.train.aimforthehead.domain.dto.PurchaseDetailDto;
import com.train.aimforthehead.domain.entities.PurchaseDetail;
import com.train.aimforthehead.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PurchaseDetailMapperImpl implements Mapper<PurchaseDetail, PurchaseDetailDto> {
    private ModelMapper modelMapper;

    public PurchaseDetailMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PurchaseDetail mapFrom(PurchaseDetailDto purchaseDetailDto) {
        return modelMapper.map(purchaseDetailDto, PurchaseDetail.class);
    }

    @Override
    public PurchaseDetailDto mapTo(PurchaseDetail purchaseDetail) {
        return modelMapper.map(purchaseDetail, PurchaseDetailDto.class);
    }
}
