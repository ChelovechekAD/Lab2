package org.unitasks.services;

import org.hibernate.type.EmbeddedComponentType;
import org.unitasks.DAO.AuditoryDAO;
import org.unitasks.DAO.ClassUniDAO;
import org.unitasks.DAO.DisciplineDAO;
import org.unitasks.DAO.Impl.AuditoryDAOImpl;
import org.unitasks.DAO.Impl.ClassUniDAOImpl;
import org.unitasks.DAO.Impl.DisciplineDAOImpl;
import org.unitasks.DAO.Impl.ProfessorDAOImpl;
import org.unitasks.DAO.ProfessorDAO;
import org.unitasks.DTO.response.CountClassesEachDayDTOResponse;
import org.unitasks.models.Auditory;
import org.unitasks.models.ClassUni;
import org.unitasks.models.Discipline;
import org.unitasks.models.Professor;
import org.unitasks.models.embeddable.ClassPK;
import org.unitasks.utils.TransactionHelper;

import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.time.DayOfWeek;
import java.util.List;

public final class InfoService {

    private InfoService() {

    }

    private static final AuditoryDAO AUDITORY_DAO = new AuditoryDAOImpl();
    private static final ClassUniDAO CLASS_UNI_DAO = new ClassUniDAOImpl();
    private static final DisciplineDAO DISCIPLINE_DAO = new DisciplineDAOImpl();
    private static final ProfessorDAO PROFESSOR_DAO = new ProfessorDAOImpl();
    private static final TransactionHelper TRANSACTION_HELPER = TransactionHelper.getTransactionHelper();
    private static final CriteriaBuilder CRITERIA_BUILDER = TRANSACTION_HELPER.criteriaBuilder();
    private static final Metamodel METAMODEL = TRANSACTION_HELPER.metamodel();

    public static List<Professor> getAllProfWithClassesOnDayAndAuditoryNum(DayOfWeek dayOfWeek, String auditory) {

        CriteriaQuery<Professor> cq = CRITERIA_BUILDER.createQuery(Professor.class);
        Root<Professor> root = cq.from(Professor.class);

        EntityType<Professor> Professor_ = METAMODEL.entity(Professor.class);
        EntityType<Discipline> Discipline_ = METAMODEL.entity(Discipline.class);

        Join<Professor, Discipline> disciplineJoin = root.join(Professor_.getAttribute("disciplineList").getName());
        Join<Discipline, Auditory> auditoryJoin = disciplineJoin.join(Discipline_.getAttribute("auditoryList").getName());

        Predicate predicates = CRITERIA_BUILDER.conjunction();
        predicates.getExpressions().add(CRITERIA_BUILDER.equal(auditoryJoin.get("auditoryNum"), auditory));
        predicates.getExpressions().add(CRITERIA_BUILDER.equal(disciplineJoin.get("dayOfWeek"), dayOfWeek));

        cq.select(root).where(predicates);
        return TRANSACTION_HELPER.entityManager().createQuery(cq).getResultList();
    }

    public static List<Professor> getAllProfWithoutClassesOnTheSelectedDay(DayOfWeek dayOfWeek) {

        CriteriaQuery<Professor> cq = CRITERIA_BUILDER.createQuery(Professor.class);
        Root<Professor> root = cq.from(Professor.class);

        EntityType<Professor> Professor_ = METAMODEL.entity(Professor.class);

        Join<Professor, Discipline> disciplineJoin = root.join(Professor_.getAttribute("disciplineList").getName());

        cq.select(root).where(CRITERIA_BUILDER.notEqual(disciplineJoin.get("dayOfWeek"), dayOfWeek));
        return TRANSACTION_HELPER.entityManager().createQuery(cq).getResultList();
    }

    public static List<CountClassesEachDayDTOResponse> getAllDaysWithCountOfClassesMoreThen(int count) {

        CriteriaQuery<DayOfWeek> cq = CRITERIA_BUILDER.createQuery(DayOfWeek.class);
        Root<ClassUni> root = cq.from(ClassUni.class);

        EntityType<ClassUni> ClassUni_ = METAMODEL.entity(ClassUni.class);

        Join<ClassUni, ClassPK> classUniClassPKJoin = root.join(ClassUni_.getAttribute("classPK").getName());
        Join<ClassPK, Discipline> disciplineJoin = classUniClassPKJoin.join("discipline");
        //TODO Из селетка вернуть день недели и сумму занятий в этот день. Хз как запихнуть это в дто.
        cq.select(disciplineJoin.get("dayOfWeek")/*, CRITERIA_BUILDER.sum(root.get("countOfClass"))*/)
                .groupBy(disciplineJoin.get("dayOfWeek"))
                .having(CRITERIA_BUILDER.ge(CRITERIA_BUILDER.sum(root.get("countOfClass")), count));
        TRANSACTION_HELPER.entityManager().createQuery(cq).getResultList().forEach(System.out::println);
        return null;
    }


}
