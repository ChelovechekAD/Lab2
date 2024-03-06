package org.unitasks.DTO.response;

import lombok.*;

import java.time.DayOfWeek;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CountClassesEachDayDTOResponse {
    private DayOfWeek dayOfWeek;
    private Integer countOfClasses;
}
