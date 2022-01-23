package com.schwarz.onlinelibrary.repository;

import com.schwarz.onlinelibrary.entities.Entity;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class IdGeneratorImpl implements IdGenerator {

    private Long incrementLong(Long id) {
        return ++ id;
    }

    @Override
    public Long generateId(Set<? extends Entity> entitiesStore) {
        if (entitiesStore == null || entitiesStore.isEmpty()) {
            return 1L;
        }

        return entitiesStore.stream()
                .map(Entity::getId)
                .max(Long::compareTo)
                .map(this::incrementLong)
                .orElse(1L);
    }
}
