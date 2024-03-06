package org.unitasks.utils;

import java.util.List;
import java.util.Random;

public interface Constants {
    Random RANDOM = new Random();
    String SOMETHING_WENT_WRONG_DURING_EXECUTE_TRANSACTIONS_METHODS =
            "Something went wrong during execute transaction's methods.";
    String ENTITY_MANAGER_IS_NOT_ALIVE = "Entity Manager is not alive.";
    String TRANSACTION_IS_NOT_ACTIVE = "Transaction is not active.";
    String ROLLBACK_ABORTED_SOMETHING_WENT_WRONG = "Rollback aborted. Something went wrong.";
    String COMMIT_ABORTED_SOMETHING_WENT_WRONG = "Commit aborted. Something went wrong.";
    String NULL_EXCEPTION_MESSAGE = "Try to use null object. Aborted.";
    List<String> LIST_OF_PROF_NAMES_M = List.of("Artem", "Oleg", "Nikita", "Anton", "Semen", "Maksim");
    List<String> LIST_OF_PROF_NAMES_W = List.of("Olga", "Anastasia", "Karina", "Aleksandra");
    List<String> LIST_OF_PROF_SURNAMES_M = List.of("Smirnov", "Ivanov", "Sokolov", "Popov",
            "Kozlov", "Petrov", "Vinogradov", "Bogdanov", "Fedorov");
    List<String> LIST_OF_PROF_SURNAMES_W = List.of("Smirnova", "Ivanova", "Sokolova", "Popova",
            "Kozlova", "Petrova", "Vinogradova", "Bogdanova", "Fedorova");
    List<String> LIST_OF_PROF_MIDDLE_NAMES_M = List.of("Vyacheslavovich", "Anatolyevich", "Aleksandrovich",
            "Yuryevich", "Victorovich", "Valeryevich");
    List<String> LIST_OF_PROF_MIDDLE_NAMES_W = List.of("Vyacheslavovna", "Anatolyevna", "Aleksandrovna",
            "Yuryevna", "Victorovna", "Valeryevna");
    List<String> LIST_OF_DISCIPLINE_TITLES = List.of("VSIS", "OMO", "Philosophy", "Math", "Algebra", "TBS", "BSI");
    int FLOOR_MULTIPLIER = 100;
    int NUMBER_OF_FLOORS = 5;
    int MAX_NUMBER_OF_CLASSROOMS = 20;
    int MAX_COUNT_OF_LESSONS_EACH_DISCIPLINE = 5;
    int NULL_AVOIDANCE_RAND_VALUE = 1;
    int MIN_COUNT_OF_STUDENT_EACH_CLASS = 20;
    int MAX_COUNT_OF_STUDENT_EACH_CLASS = 50;
}
