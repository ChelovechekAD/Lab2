package org.unitasks.models.embeddable;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.unitasks.models.Discipline;
import org.unitasks.models.Professor;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@SuperBuilder
@NoArgsConstructor
public class ClassPK implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DISCIPLINE_ID")
    private Discipline discipline;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PROFESSOR_ID")
    private Professor professor;

}
