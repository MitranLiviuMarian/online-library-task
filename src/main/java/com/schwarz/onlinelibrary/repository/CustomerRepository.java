package com.schwarz.onlinelibrary.repository;

import com.schwarz.onlinelibrary.entities.CustomerEntity;
import com.schwarz.onlinelibrary.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@org.springframework.stereotype.Repository
public class CustomerRepository implements Repository<CustomerEntity> {
    private static final Set<CustomerEntity> customerStore = new LinkedHashSet<>();

    @Autowired
    private IdGenerator customerIdGenerator;

    @Override
    public Set<CustomerEntity> findAll() {
        return customerStore;
    }

    @Override
    public CustomerEntity findById(Long id) {
        return customerStore.stream()
                .filter(customer -> Objects.equals(customer.getId(), id))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Customer with id: " + id + " not found"));
    }

    @Override
    public CustomerEntity delete(Long id) {
        CustomerEntity existingCustomer = findById(id);
        customerStore.remove(existingCustomer);
        return existingCustomer;
    }

    @Override
    public CustomerEntity save(CustomerEntity entity) {
        Long newId = customerIdGenerator.generateId(customerStore);
        entity.setId(newId);
        customerStore.add(entity);
        return entity;
    }

    @Override
    public CustomerEntity update(CustomerEntity entity) {
        CustomerEntity existingEntity = findById(entity.getId());
        customerStore.remove(existingEntity);
        customerStore.add(entity);
        return entity;
    }
}
