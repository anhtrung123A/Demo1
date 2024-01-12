package com.train.aimforthehead.controllers.rest;

import com.train.aimforthehead.domain.dto.BookDto;
import com.train.aimforthehead.domain.dto.CustomerDto;
import com.train.aimforthehead.domain.entities.BookEntity;
import com.train.aimforthehead.domain.entities.CustomerEntity;
import com.train.aimforthehead.mappers.Mapper;
import com.train.aimforthehead.repositories.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class CustomerController {
    private Mapper<CustomerEntity, CustomerDto> customerMapper;
    private CustomerRepository customerRepository;

    public CustomerController(Mapper<CustomerEntity, CustomerDto> customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @GetMapping(path = "/customers")
    public String showCustomers(Model model)
    {
        model.addAttribute("customer", new CustomerDto());
        return "customers";
    }
    @PostMapping(path = "/customers")
    public String showCustomers(@ModelAttribute("customer") CustomerDto customer)
    {
        CustomerEntity customerEntity = customerMapper.mapFrom(customer);
        customerRepository.save(customerEntity);
        return "books";
    }
}
