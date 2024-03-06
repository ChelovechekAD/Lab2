package org.unitasks.DAO.Impl;

import org.unitasks.DAO.ClassUniDAO;
import org.unitasks.models.ClassUni;

public class ClassUniDAOImpl extends DAOImpl<ClassUni, Integer> implements ClassUniDAO {
    @Override
    protected Class<ClassUni> getClazz() {
        return ClassUni.class;
    }
}
