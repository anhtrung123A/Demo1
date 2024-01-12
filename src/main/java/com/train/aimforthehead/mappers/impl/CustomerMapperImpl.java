package com.train.aimforthehead.mappers.impl;

import com.train.aimforthehead.domain.dto.CustomerDto;
import com.train.aimforthehead.domain.entities.CustomerEntity;
import com.train.aimforthehead.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapperImpl implements Mapper<CustomerEntity, CustomerDto> {
    private ModelMapper modelMapper;

    public CustomerMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CustomerEntity mapFrom(CustomerDto customerDto) {
        return modelMapper.map(customerDto, CustomerEntity.class);
    }

    @Override
    public CustomerDto mapTo(CustomerEntity customerEntity) {
        return modelMapper.map(customerEntity, CustomerDto.class);
    }
}
