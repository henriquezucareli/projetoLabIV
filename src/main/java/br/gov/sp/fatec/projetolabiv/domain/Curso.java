package br.gov.sp.fatec.projetolabiv.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table  (name = "curso")
public class Curso {
    @Id
    private Integer id;
    private String nome;
    private String periodicidade;
    private String descricao;

}
