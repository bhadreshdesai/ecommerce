package bdd.demo.ecommerce.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class BaseService<E extends BaseEntity<ID>, ID, R extends PagingAndSortingRepository<E, ID>> {
    @Autowired
    protected R repository;

    @Transactional
    public ID create(E entity) {
        entity = repository.save(entity);
        return entity.getId();
    }

    @Transactional
    public E update(ID id, E entity) {
        // Merge update https://www.baeldung.com/spring-data-partial-update
        Optional<E> existingEntity = repository.findById(id);
        if(!existingEntity.isPresent()) {
            throw new EntityNotFoundException();
        }
        entity.setId(id);
        return repository.save(entity);
    }

    @Transactional(readOnly = true)
    public Optional<E> getById(@NotNull ID id) {
        return repository.findById(id);
    }

    @Transactional
    public void deleteById(@NotNull ID id) {
        repository.deleteById(id);
    }
}
