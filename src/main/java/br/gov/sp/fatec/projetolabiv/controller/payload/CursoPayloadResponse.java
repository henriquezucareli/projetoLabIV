package br.gov.sp.fatec.projetolabiv.controller.payload;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class CursoPayloadResponse {

    private Integer id;
    private String nome;
    private String periodicidade;
    private String descricao;


}
