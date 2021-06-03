package br.gov.sp.fatec.projetolabiv.controller.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class CursoPayloadRequest {
    @NotBlank(message = "Nome obrigatório!")
    @Size(max = 255, message = "Nome não deve conter mais de 255 caracteres")
    private String nome;
    @NotBlank(message = "Periodicidade obrigatória!")
    @Size(max = 255, message = "Periodicidade não deve conter mais de 255 caracteres")
    private String periodicidade;
    @NotBlank(message = "Descrição obrigatória!")
    @Size(max = 255, message = "Descrição não deve conter mais de 255 caracteres")
    private String descricao;
}
