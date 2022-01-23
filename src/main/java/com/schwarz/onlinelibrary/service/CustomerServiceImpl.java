package com.schwarz.onlinelibrary.service;

import com.schwarz.onlinelibrary.entities.CustomerEntity;
import com.schwarz.onlinelibrary.model.CustomerDto;
import com.schwarz.onlinelibrary.model.LoginDto;
import com.schwarz.onlinelibrary.repository.Repository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private Repository<CustomerEntity> customerRepository;

    @Autowired
    private ModelMapper customerMapper;

    @Override
    public CustomerDto saveCustomer(CustomerDto customer) {
        CustomerEntity customerEntity = toEntity(customer);
        return toDto(customerRepository.save(customerEntity));
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customer) {
        CustomerEntity customerEntity = toEntity(customer);
        return toDto(customerRepository.update(customerEntity));
    }

    @Override
    public CustomerDto deleteCustomer(Long id) {
        return toDto(customerRepository.delete(id));
    }

    @Override
    public CustomerDto findCustomer(Long id) {
        return toDto(customerRepository.findById(id));
    }

    @Override
    public Optional<CustomerDto> findByUsernameAndPassword(LoginDto loginDto) {
        return findAllCustomers().stream()
                .filter(customerDto -> Objects.equals(customerDto.getPassword(), loginDto.getPassword()))
                .filter(customerDto -> Objects.equals(customerDto.getEmail(), loginDto.getEmail()))
                .findFirst();
    }

    @Override
    public Set<CustomerDto> findAllCustomers() {
        return customerRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toSet());
    }


    private CustomerDto toDto(CustomerEntity customerEntity) {
        return customerMapper.map(customerEntity, CustomerDto.class);
    }

    private CustomerEntity toEntity(CustomerDto customerDto) {
        return customerMapper.map(customerDto, CustomerEntity.class);
    }
}
