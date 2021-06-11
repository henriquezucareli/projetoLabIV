package br.gov.sp.fatec.projetolabiv.controller.payload;

import br.gov.sp.fatec.projetolabiv.domain.Turma;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AulaPayloadResponse {
    private Integer id;
    private TurmaPayloadResponse turma;
    private DisciplinaPayloadResponse disciplina;
    private ProfessorPayloadResponse professor;
    private CursoPayloadResponse curso;
}
