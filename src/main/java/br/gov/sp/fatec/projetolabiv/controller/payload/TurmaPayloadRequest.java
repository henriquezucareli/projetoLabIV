package br.gov.sp.fatec.projetolabiv.controller.payload;

import br.gov.sp.fatec.projetolabiv.domain.Curso;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class TurmaPayloadRequest {

    @NotBlank(message = "Descrição obrigatória!")
    @Size(max = 255, message = "Descrição não deve conter mais de 255 caracteres")
    private String descricao;

    private Set<Integer> alunos;

    @NotNull(message = "Curso obrigatório!")
    private Integer curso;

}
