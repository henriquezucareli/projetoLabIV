package br.gov.sp.fatec.projetolabiv.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "professor")
@EqualsAndHashCode(exclude = {"disciplinas"})
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String titulacao;
    @ManyToMany
    @JoinTable(name = "professor_disciplina",
            inverseJoinColumns = {@JoinColumn(name = "disciplina_id")},
            joinColumns = {@JoinColumn(name = "professor_id")})

    private Set<Disciplina> disciplinas = new HashSet<>();

    public void addDisciplina(final Disciplina pDisciplina) {
        this.getDisciplinas().add(pDisciplina);
        pDisciplina.getProfessores().add(this);
    }

    public void addDisciplina(final List<Disciplina> disciplinas) {
        disciplinas.forEach(this::addDisciplina);
    }

    public void removeDisciplina(final Disciplina pDisciplina) {
        this.getDisciplinas().remove(pDisciplina);
        pDisciplina.getProfessores().remove(this);
    }

    public void clearDisciplinas() {
        disciplinas.forEach(this::removeDisciplina);
    }
}
