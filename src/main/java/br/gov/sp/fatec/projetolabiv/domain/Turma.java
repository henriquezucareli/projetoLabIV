package br.gov.sp.fatec.projetolabiv.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "turma")
public class Turma {
    @Id
    private Integer id;
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;
    @OneToMany
    private Set<Aluno> alunos;

}
