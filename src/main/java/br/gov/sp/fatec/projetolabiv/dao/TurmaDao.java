package br.gov.sp.fatec.projetolabiv.dao;

import br.gov.sp.fatec.projetolabiv.domain.Turma;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TurmaDao extends CrudRepository<Turma, Integer> {

    @Query("SELECT t FROM Turma t JOIN t.curso c WHERE (c.id = :curso OR :curso is null)")
    List<Turma> findByCurso(@Param("curso") Integer curso);
}
