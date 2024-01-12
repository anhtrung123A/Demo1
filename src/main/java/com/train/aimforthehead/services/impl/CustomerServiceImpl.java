package com.train.aimforthehead.services.impl;

import com.train.aimforthehead.repositories.CustomerRepository;
import com.train.aimforthehead.services.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
}
