package org.unitasks.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "discipline")
public class Discipline implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column
    private String title;

    @Column(name = "day_of_week")
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "discipline_auditory",
            joinColumns = {@JoinColumn(name = "discipline_id")},
            inverseJoinColumns = {@JoinColumn(name = "auditory_id")}
    )
    private Set<Auditory> auditoryList;

    public void addAuditory(Auditory auditory){
        auditoryList.add(auditory);
        auditory.getDisciplineList().add(this);
    }

    public void setAuditoryList(Set<Auditory> auditoryList){
        this.auditoryList = auditoryList;
    }

    public void removeAuditory(Auditory auditory){
        auditoryList.remove(auditory);
        auditory.getDisciplineList().remove(this);
    }

}
