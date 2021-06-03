package br.gov.sp.fatec.projetolabiv.controller.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class ProfessorPayloadRequest {
    @NotBlank(message = "Nome obrigatório!")
    @Size(max = 255, message = "Nome não deve conter mais de 255 caracteres")
    private String nome;
    @NotBlank(message = "Titulação obrigatória!")
    @Size(max = 255, message = "Titulação não deve conter mais de 255 caracteres")
    private String titulacao;
    private Set<Integer> disciplinas;
}
