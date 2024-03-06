package org.unitasks.models;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.unitasks.models.embeddable.ClassPK;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "class_uni")
public class ClassUni implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Embedded
    private ClassPK classPK;

    @Column(name = "count_of_students")
    private Integer countOfStudents;

    @Column(name = "count_of_class")
    private Integer countOfClass;

}
