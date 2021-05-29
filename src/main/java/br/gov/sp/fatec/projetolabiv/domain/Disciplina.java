package br.gov.sp.fatec.projetolabiv.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "disciplina")
public class Disciplina {
    @Id
    private Integer id;
    private String nome;
    private Integer cargaHoraria;
    private String ementa;
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
    @ManyToMany
    @JoinTable(name = "professor_disciplina",
            joinColumns = { @JoinColumn(name = "professor_id") },
            inverseJoinColumns = { @JoinColumn(name = "disciplina_id") })
    private Set<Professor> professores;
}
