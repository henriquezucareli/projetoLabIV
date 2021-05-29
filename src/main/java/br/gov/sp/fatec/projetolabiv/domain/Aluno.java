package br.gov.sp.fatec.projetolabiv.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "aluno")
public class Aluno {
    @Id
    private Integer id;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

}
