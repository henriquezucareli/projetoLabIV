package br.gov.sp.fatec.projetolabiv.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table  (name = "professor")
public class Professor {
    @Id
    private Integer id;
    private String nome;
    private String titulacao;
    @ManyToMany
    @JoinTable(name = "professor_disciplina",
            joinColumns = { @JoinColumn(name = "disciplina_id") },
            inverseJoinColumns = { @JoinColumn(name = "professor_id") })
    private Set<Disciplina> disciplinas;


}
