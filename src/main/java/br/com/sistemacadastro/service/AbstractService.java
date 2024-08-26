package br.com.sistemacadastro.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.sistemacadastro.utils.Model;

public abstract class AbstractService<T extends Model<ID>, ID extends Serializable> implements Serializable {

    private final Class<T> type;

    @PersistenceContext
    private EntityManager entityManager;

    public AbstractService(Class<T> type) {
        this.type = type;
    }

    public Optional<T> findById(ID id) {
        return Optional.ofNullable(this.entityManager.find(type, id));
    }

    public List<T> findAll() {
        return this.entityManager.createQuery("SELECT t FROM " + type.getSimpleName() + " t", type).getResultList();
    }

    @Transactional
    public void remove(ID id) {
        findById(id).ifPresent(entity -> this.entityManager.remove(entity));
    }

    @Transactional
    public void create(T object) {
        this.entityManager.persist(object);
    }

    @Transactional
    public void update(T object) {
        this.entityManager.merge(object);
    }

    @Transactional
    public void save(T object) {
        if (object.getId() == null) {
            this.create(object);
        } else {
            this.update(object);
        }
    }
}
