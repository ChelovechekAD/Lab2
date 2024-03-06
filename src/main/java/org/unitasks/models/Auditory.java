package org.unitasks.models;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = "disciplineList")
@ToString(exclude = "disciplineList")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "auditory")
public class Auditory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "auditory_num", unique = true)
    private String auditoryNum;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "auditoryList")
    private Set<Discipline> disciplineList;

    public void addDiscipline(Discipline discipline) {
        disciplineList.add(discipline);
        discipline.getAuditoryList().add(this);
    }

    public void setDisciplineList(Set<Discipline> disciplineList) {
        this.disciplineList = disciplineList;
    }

    public void removeDiscipline(Discipline discipline) {
        disciplineList.remove(discipline);
        discipline.getAuditoryList().remove(this);
    }
}
