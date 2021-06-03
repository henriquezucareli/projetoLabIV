package br.gov.sp.fatec.projetolabiv.controller.payload;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class AlunoPayloadResponse {
    private Integer id;
    private String nome;
    private TurmaPayloadResponse turma;
}
