package br.gov.sp.fatec.projetolabiv.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "turma")
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;
    @OneToMany
    private Set<Aluno> alunos = new HashSet<>();
    @OneToMany
    private Set<Aula> aulas = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public void addAluno(final Aluno pAluno) {
        this.getAlunos().add(pAluno);
        pAluno.setTurma(this);
    }

    public void addAluno(final List<Aluno> alunos) {
        alunos.forEach(this::addAluno);
    }

    public void removeAluno(final Aluno pAluno) {
        this.getAlunos().remove(pAluno);
        pAluno.setTurma(null);
    }

    public void clearAlunos() {
        alunos.forEach(this::removeAluno);
    }



}
