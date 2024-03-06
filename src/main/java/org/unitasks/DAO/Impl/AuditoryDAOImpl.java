package org.unitasks.DAO.Impl;

import org.unitasks.DAO.AuditoryDAO;
import org.unitasks.models.Auditory;
import org.unitasks.models.Discipline;

public class AuditoryDAOImpl extends DAOImpl<Auditory, Integer> implements AuditoryDAO {
    @Override
    protected Class<Auditory> getClazz() {
        return Auditory.class;
    }

    @Override
    public boolean delete(Integer id) {
        transactionHelper.begin();
        try {
            Auditory auditory = transactionHelper.find(getClazz(), id);
            transactionHelper.entityManager()
                    .createNativeQuery("delete from lab2.discipline_auditory da where da.auditory_id = " + auditory.getId())
                    .executeUpdate();
            transactionHelper.remove(auditory);
            Auditory auditory1 = transactionHelper.find(getClazz(), id);
            transactionHelper.commit();
            return auditory1 == null;
        } catch (Exception e) {
            e.printStackTrace();
            transactionHelper.rollback();
        }
        return false;
    }
}
