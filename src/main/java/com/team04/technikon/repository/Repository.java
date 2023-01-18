package com.team04.technikon.repository;

import com.team04.technikon.model.PersistentClass;

import java.util.Optional;

public interface Repository<T extends PersistentClass> {

    int create(T t);

    T read(int id);
    
    Optional<T> searchById(int id);

    boolean removeEntity(int id);

    Optional<T> update(int id, T t);

}
