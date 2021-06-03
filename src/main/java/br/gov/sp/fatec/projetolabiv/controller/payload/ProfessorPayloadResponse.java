package br.gov.sp.fatec.projetolabiv.controller.payload;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Builder
public class ProfessorPayloadResponse {

    private Integer id;
    private String nome;
    private String titulacao;
    private Set<DisciplinaPayloadResponse> disciplinas;


}
