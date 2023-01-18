package com.team04.technikon.repository.impl;

import com.team04.technikon.model.PersistentClass;
import com.team04.technikon.repository.Repository;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.transaction.UserTransaction;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class RepositoryImpl<T extends PersistentClass> implements Repository<T>, Serializable {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private UserTransaction userTransaction;

    public abstract String getClassName();
    public abstract Class<T> findClass();
    public abstract void updateFields(T tSource, T tTarget);

    @Override
    @Transactional
    public int create(T t) {
        entityManager.persist(t);
        return t.getId();
    }
    
    @Override
    public T read(int id) {
        return entityManager.find(findClass(), id);
    }


    @Override
    public Optional<T> searchById(int id) {
        T t = entityManager.find(findClass(), id);
        return t != null ? Optional.of(t) : Optional.empty();
    }

    @Override
    public boolean removeEntity(int id) {
        T persistentInstance = entityManager.find(findClass(), id);
        if (persistentInstance == null) {
            return false;
        }
        try {
            userTransaction.begin();
            entityManager.remove(entityManager.contains(persistentInstance) ? persistentInstance : entityManager.merge(persistentInstance));
            userTransaction.commit();
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
        return true;
    }

    @Override
    public Optional<T> update(int id, T t1) {
       try {
            userTransaction.begin();
            T t0 = entityManager.find(findClass(), id);
            if (t0 == null) {
                userTransaction.commit();
                return Optional.empty();
            }
            updateFields(t0, t1);
            entityManager.persist(entityManager.contains(t0) ? t0 : entityManager.merge(t0));
            userTransaction.commit();
            return Optional.of(t0);
        } catch (Exception e) {
            System.out.println(e.toString());
            return Optional.empty();
        }
    }

}
