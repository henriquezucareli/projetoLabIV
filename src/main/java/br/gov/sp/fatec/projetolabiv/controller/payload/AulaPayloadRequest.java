package br.gov.sp.fatec.projetolabiv.controller.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AulaPayloadRequest {

    @NotNull(message = "Turma obrigatória!")
    private Integer turma;

    @NotNull(message = "Professor obrigatório!")
    private Integer professor;

    @NotNull(message = "Disciplina obrigatória!")
    private Integer disciplina;

    @NotNull(message = "Curso obrigatório!")
    private Integer curso;

}
