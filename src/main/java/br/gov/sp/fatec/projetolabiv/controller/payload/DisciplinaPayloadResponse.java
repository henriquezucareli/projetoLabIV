package br.gov.sp.fatec.projetolabiv.controller.payload;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class DisciplinaPayloadResponse {
    private Integer id;
    private String nome;
    private Integer cargaHoraria;
    private String ementa;
    private CursoPayloadResponse curso;
    private Set<ProfessorPayloadResponse> professores;
}
