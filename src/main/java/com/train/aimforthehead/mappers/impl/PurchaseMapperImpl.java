package com.train.aimforthehead.mappers.impl;

import com.train.aimforthehead.domain.dto.PurchaseDto;
import com.train.aimforthehead.domain.entities.PurchaseEntity;
import com.train.aimforthehead.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PurchaseMapperImpl implements Mapper<PurchaseEntity, PurchaseDto> {
    private ModelMapper modelMapper;

    public PurchaseMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PurchaseEntity mapFrom(PurchaseDto purchaseDto) {
        return modelMapper.map(purchaseDto, PurchaseEntity.class);
    }

    @Override
    public PurchaseDto mapTo(PurchaseEntity purchaseEntity) {
        return modelMapper.map(purchaseEntity, PurchaseDto.class);
    }
}
