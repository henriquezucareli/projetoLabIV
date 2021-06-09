package br.gov.sp.fatec.projetolabiv.controller.payload;

import br.gov.sp.fatec.projetolabiv.domain.Aluno;
import br.gov.sp.fatec.projetolabiv.domain.Curso;
import br.gov.sp.fatec.projetolabiv.domain.Disciplina;
import br.gov.sp.fatec.projetolabiv.domain.Professor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class TurmaPayloadResponse {

    private Integer id;
    private String descricao;
    private Set<AlunoPayloadResponse> alunos;
    private CursoPayloadResponse curso;


}
