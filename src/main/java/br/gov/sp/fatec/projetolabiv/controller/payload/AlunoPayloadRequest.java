package br.gov.sp.fatec.projetolabiv.controller.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class AlunoPayloadRequest {

    @NotBlank(message = "Nome obrigatório!")
    @Size(max = 255, message = "Nome não deve conter mais de 255 caracteres")
    private String nome;

    private Integer turma;

}
