package com.schwarz.onlinelibrary.repository;

import com.schwarz.onlinelibrary.entities.Entity;

import java.util.Set;

public interface IdGenerator {

    /**
     * Computes a unique ID by incrementing the last id from the current list of entities.
     * @param entitiesStore current list of entities
     * @return a unique id for the entity
     */
    Long generateId(Set<? extends Entity> entitiesStore);
}
