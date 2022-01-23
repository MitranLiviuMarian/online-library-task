package com.schwarz.onlinelibrary.service;

import com.schwarz.onlinelibrary.model.BookDto;
import com.schwarz.onlinelibrary.model.CustomerDto;
import com.schwarz.onlinelibrary.model.LoginDto;

import java.util.Optional;
import java.util.Set;

public interface CustomerService {

    /**
     * Saves a customer.
     * @param customer submitted customer
     * @return the new created customer
     */
    CustomerDto saveCustomer(CustomerDto customer);

    /**
     * Upates a customer if exists.
     * @param customer updated customer
     * @return the updated customer
     */
    CustomerDto updateCustomer(CustomerDto customer);

    /**
     * Deletes a customer by id.
     * @param id of the customer
     * @return deleted customer
     */
    CustomerDto deleteCustomer(Long id);

    /**
     * Finds a customer by id.
     * @param id id of the customer
     * @return found customer
     */
    CustomerDto findCustomer(Long id);

    /**
     * Finds a customer by his credentials
     * @param loginDto credentials infp
     * @return found customer
     */
    Optional<CustomerDto> findByUsernameAndPassword(LoginDto loginDto);

    /**
     * Finds all customers available.
     * @return a set of all customers
     */
    Set<CustomerDto> findAllCustomers();

}
