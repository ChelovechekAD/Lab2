package org.unitasks.utils;

import org.unitasks.models.Auditory;
import org.unitasks.models.ClassUni;
import org.unitasks.models.Discipline;
import org.unitasks.models.Professor;
import org.unitasks.models.embeddable.ClassPK;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.unitasks.utils.Constants.*;

public final class DataGenerator {

    private DataGenerator() {

    }

    public static List<Professor> generateProfessorList(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> generateProfessor())
                .collect(Collectors.toList());
    }

    public static List<Discipline> generateDisciplineList(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> generateDiscipline())
                .collect(Collectors.toList());
    }

    public static Set<Auditory> generateAuditoryList(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> generateAuditory())
                .collect(Collectors.toSet());
    }

    public static List<ClassUni> generateClassUniList(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> generateClassUni())
                .collect(Collectors.toList());
    }

    public static List<ClassUni> generateClassUniList(int count, List<Professor> professorList,
                                                      List<Discipline> disciplineList) {
        return IntStream.range(0, count)
                .mapToObj(i -> {
                    ClassUni classUni = generateClassUni();
                    classUni.setClassPK(generateClassPK(professorList, disciplineList));
                    return classUni;
                }).collect(Collectors.toList());
    }

    private static Professor generateProfessor() {
        int gender = RANDOM.nextInt(2);
        return Professor.builder()
                .name((gender == 1) ?
                        LIST_OF_PROF_NAMES_M.get(RANDOM.nextInt(LIST_OF_PROF_NAMES_M.size())) :
                        LIST_OF_PROF_NAMES_W.get(RANDOM.nextInt(LIST_OF_PROF_NAMES_W.size())))
                .surname((gender == 1) ?
                        LIST_OF_PROF_SURNAMES_M.get(RANDOM.nextInt(LIST_OF_PROF_SURNAMES_M.size())) :
                        LIST_OF_PROF_SURNAMES_W.get(RANDOM.nextInt(LIST_OF_PROF_SURNAMES_W.size())))
                .middleName((gender == 1) ?
                        LIST_OF_PROF_MIDDLE_NAMES_M.get(RANDOM.nextInt(LIST_OF_PROF_MIDDLE_NAMES_M.size())) :
                        LIST_OF_PROF_MIDDLE_NAMES_W.get(RANDOM.nextInt(LIST_OF_PROF_MIDDLE_NAMES_M.size())))
                .disciplineList(new HashSet<>())
                .build();
    }

    private static Discipline generateDiscipline() {
        return Discipline.builder()
                .title(LIST_OF_DISCIPLINE_TITLES.get(RANDOM.nextInt(LIST_OF_DISCIPLINE_TITLES.size())))
                .dayOfWeek(DayOfWeek.of(RANDOM.nextInt(DayOfWeek.values().length) + NULL_AVOIDANCE_RAND_VALUE))
                .auditoryList(new HashSet<>())
                .build();
    }

    private static Auditory generateAuditory() {
        int num = ((RANDOM.nextInt(NUMBER_OF_FLOORS) + NULL_AVOIDANCE_RAND_VALUE) * FLOOR_MULTIPLIER)
                + RANDOM.nextInt(MAX_NUMBER_OF_CLASSROOMS);
        return Auditory.builder()
                .auditoryNum(String.valueOf(num))
                .disciplineList(new HashSet<>())
                .build();
    }

    private static ClassUni generateClassUni() {
        return ClassUni.builder()
                .countOfClass(RANDOM.nextInt(MAX_COUNT_OF_LESSONS_EACH_DISCIPLINE) + NULL_AVOIDANCE_RAND_VALUE)
                .countOfStudents(RANDOM.nextInt(MAX_COUNT_OF_STUDENT_EACH_CLASS - MIN_COUNT_OF_STUDENT_EACH_CLASS)
                        + MIN_COUNT_OF_STUDENT_EACH_CLASS)
                .build();
    }

    private static ClassPK generateClassPK(List<Professor> professorList, List<Discipline> disciplineList) {
        return ClassPK.builder()
                .discipline(disciplineList.get(RANDOM.nextInt(disciplineList.size())))
                .professor(professorList.get(RANDOM.nextInt(professorList.size())))
                .build();
    }

}
