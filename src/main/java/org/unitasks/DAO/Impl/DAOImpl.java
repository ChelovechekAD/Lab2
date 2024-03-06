package org.unitasks.DAO.Impl;

import org.unitasks.DAO.DAO;
import org.unitasks.utils.Constants;
import org.unitasks.utils.TransactionHelper;

import java.io.Serializable;
import java.util.function.Supplier;

public abstract class DAOImpl<T extends Serializable, R extends Number> implements DAO<T, R> {

    protected abstract Class<T> getClazz();
    protected TransactionHelper transactionHelper;

    {
        transactionHelper = TransactionHelper.getTransactionHelper();
    }

    public T save(T obj) {
        if (obj == null){
            System.out.println(Constants.NULL_EXCEPTION_MESSAGE);
            return null;
        }
        Supplier<T> method = () -> {
            transactionHelper.persist(obj);
            return obj;
        };

        return transactionHelper.transaction(method);
    }

    public T get(R id) {
        return transactionHelper.find(getClazz(), id);
    }

    public T update(T obj) {
        if (obj == null){
            System.out.println(Constants.NULL_EXCEPTION_MESSAGE);
            return null;
        }
        Supplier<T> method = () -> {
            transactionHelper.merge(obj);
            return obj;
        };

        return transactionHelper.transaction(method);
    }

    public boolean delete(R id) {
        transactionHelper.begin();
        try {
            T obj = transactionHelper.find(getClazz(), id);
            if (obj == null){
                System.out.println(Constants.NULL_EXCEPTION_MESSAGE);
                return false;
            }
            transactionHelper.remove(obj);
            obj = transactionHelper.find(getClazz(), id);
            transactionHelper.commit();
            return obj == null;

        }catch (Exception e){
            e.printStackTrace();
            transactionHelper.rollback();
            return false;
        }
    }

}
