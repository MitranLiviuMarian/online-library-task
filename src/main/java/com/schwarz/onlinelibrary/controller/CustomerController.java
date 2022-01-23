package com.schwarz.onlinelibrary.controller;

import com.schwarz.onlinelibrary.model.CustomerDto;
import com.schwarz.onlinelibrary.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(produces = "application/json")
    public Set<CustomerDto> getCustomers() {
        return customerService.findAllCustomers();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public CustomerDto getCustomerById(@PathVariable Long id) {
        return customerService.findCustomer(id);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public CustomerDto deleteCustomerById(@PathVariable Long id) {
        return customerService.deleteCustomer(id);
    }

    @PostMapping(produces = "application/json")
    public CustomerDto createCustomer(@RequestBody @Valid CustomerDto customerDto) {
        return customerService.saveCustomer(customerDto);
    }

    @PutMapping(path = "/{id}", produces = "application/json")
    public CustomerDto updateCustomer(@RequestBody @Valid CustomerDto customerDto, @PathVariable Long id) {
        customerDto.setId(id);
        return customerService.updateCustomer(customerDto);
    }
}
