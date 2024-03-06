package org.unitasks;

import org.unitasks.DAO.AuditoryDAO;
import org.unitasks.DAO.ClassUniDAO;
import org.unitasks.DAO.DisciplineDAO;
import org.unitasks.DAO.Impl.AuditoryDAOImpl;
import org.unitasks.DAO.Impl.ClassUniDAOImpl;
import org.unitasks.DAO.Impl.DisciplineDAOImpl;
import org.unitasks.DAO.Impl.ProfessorDAOImpl;
import org.unitasks.DAO.ProfessorDAO;
import org.unitasks.models.Auditory;
import org.unitasks.models.ClassUni;
import org.unitasks.models.Discipline;
import org.unitasks.models.Professor;
import org.unitasks.services.InfoService;
import org.unitasks.utils.Constants;
import org.unitasks.utils.DataGenerator;
import org.unitasks.utils.HibernateUtil;

import javax.persistence.EntityManager;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class App 
{
    public static void main( String[] args )
    {
        ProfessorDAO professorDAO = new ProfessorDAOImpl();
        DisciplineDAO disciplineDAO = new DisciplineDAOImpl();
        AuditoryDAO auditoryDAO = new AuditoryDAOImpl();
        ClassUniDAO classUniDAO = new ClassUniDAOImpl();

        List<Professor> professorList = DataGenerator.generateProfessorList(10);
        List<Discipline> disciplineList = DataGenerator.generateDisciplineList(30);
        Set<Auditory> auditorySet = DataGenerator.generateAuditoryList(100);

        List<Auditory> auditoryList = new ArrayList<>(auditorySet);

        IntStream.range(0, disciplineList.size())
                .forEach(i -> {
                    IntStream.range(0, 10)
                            .forEach(j->disciplineList.get(i).addAuditory(
                                    auditoryList.get(Constants.RANDOM.nextInt(auditoryList.size()))));

                });

        IntStream.range(0, professorList.size())
                .forEach(i -> {
                    professorList.get(i).addDiscipline(disciplineList.get(i));
                    professorList.get(i).addDiscipline(disciplineList.get(10+i));
                    professorList.get(i).addDiscipline(disciplineList.get(20+i));
                });

        List<ClassUni> classUniList = DataGenerator.generateClassUniList(50, professorList, disciplineList);

        auditoryList.forEach(auditoryDAO::save);
        disciplineList.forEach(disciplineDAO::save);

        professorList.forEach(professorDAO::save);
        classUniList.forEach(classUniDAO::save);

        System.out.println(disciplineList.get(5));
        disciplineDAO.delete(disciplineList.get(5).getId());
        System.out.println(auditoryList.get(1));
        auditoryDAO.delete(auditoryList.get(1).getId());
        System.out.println(classUniList.get(0));
        classUniDAO.delete(classUniList.get(0).getId());

        List<Auditory> auditories = new ArrayList<>(disciplineList.get(0).getAuditoryList());
        InfoService.getAllProfWithClassesOnDayAndAuditoryNum(disciplineList.get(0).getDayOfWeek(), auditories.get(0).getAuditoryNum())
                .forEach(System.out::println);
        InfoService.getAllProfWithoutClassesOnTheSelectedDay(DayOfWeek.FRIDAY).forEach(System.out::println);
        InfoService.getAllDaysWithCountOfClassesMoreThen(20);
    }

}
