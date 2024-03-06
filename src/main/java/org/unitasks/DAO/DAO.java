package org.unitasks.DAO;

import java.io.Serializable;

public interface DAO<T extends Serializable, R extends Number> {
    T save(T obj);
    T get(R id);
    T update(T obj);
    boolean delete(R id);
}
