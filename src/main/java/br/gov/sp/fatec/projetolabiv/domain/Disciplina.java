package br.gov.sp.fatec.projetolabiv.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "disciplina")
@EqualsAndHashCode(exclude = {"professores"})
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Integer cargaHoraria;
    private String ementa;
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
    @ManyToMany
    @JoinTable(name = "professor_disciplina",
            inverseJoinColumns = { @JoinColumn(name = "professor_id") },
            joinColumns = { @JoinColumn(name = "disciplina_id") })
    private Set<Professor> professores = new HashSet<>();

    public void addProfessor(final Professor pProfessor) {
        this.getProfessores().add(pProfessor);
    }

    public void addProfessor(final List<Professor> professores) {
        professores.forEach(this::addProfessor);
    }

    public void removeProfessor(final Professor pProfessor) {
        this.getProfessores().remove(pProfessor);
    }

    public void clearProfessores() {
        professores.forEach(this::removeProfessor);
    }
}
