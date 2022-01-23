package com.schwarz.onlinelibrary.repository;

import com.schwarz.onlinelibrary.exception.EntityNotFoundException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

public interface Repository <T> {

    /**
     * Finds all entities from the store.
     * @return Set of entities
     */
    Set<T> findAll();

    /**
     * Finds one entity by id.
     * @return Found entity
     * @throws ResponseStatusException if the entity is not found
     */
    T findById(Long id);

    /**
     * Deletes one entity by id.
     * @return Deleted entity
     * @throws ResponseStatusException if the entity is not found
     */
    T delete(Long id);

    /**
     * Save an entity.
     * @return Saved entity
     */
    T save(T entity);

    /**
     * Update one existing entity
     * @return updated entity
     * @throws EntityNotFoundException if the entity is not found
     */
    T update(T entity);
}
