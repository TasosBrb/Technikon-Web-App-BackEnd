package com.team04.technikon.repository;

import com.team04.technikon.model.PersistentClass;

import java.util.List;
import java.util.Optional;

public interface Repository<T extends PersistentClass> {

    int create(T t);

    List<T> findAll();

    Optional<T> searchById(int id);

    boolean removeEntity(int id);

    Optional<T> update(int id, T t);

}
