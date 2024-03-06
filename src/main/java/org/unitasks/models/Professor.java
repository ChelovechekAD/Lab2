package org.unitasks.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode (exclude = "disciplineList")
@ToString (exclude = "disciplineList")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "professor")
public class Professor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column(name = "middle_name")
    private String middleName;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "professor_id")
    private Set<Discipline> disciplineList;

    public void addDiscipline(Discipline discipline){
        disciplineList.add(discipline);
    }

    public void setDisciplineList(Set<Discipline> disciplineList){
        this.disciplineList = disciplineList;
    }

    public void removeDiscipline(Discipline discipline){
        disciplineList.remove(discipline);
    }

}
