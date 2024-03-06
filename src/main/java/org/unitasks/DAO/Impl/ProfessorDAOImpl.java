package org.unitasks.DAO.Impl;

import org.unitasks.DAO.ProfessorDAO;
import org.unitasks.models.Professor;

public class ProfessorDAOImpl extends DAOImpl<Professor, Integer> implements ProfessorDAO {
    @Override
    protected Class<Professor> getClazz() {
        return Professor.class;
    }
}
