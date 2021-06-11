package br.gov.sp.fatec.projetolabiv.dao;

import br.gov.sp.fatec.projetolabiv.domain.Aula;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AulaDao extends CrudRepository<Aula, Integer> {

    @Query("SELECT a FROM Aula a JOIN a.turma t JOIN a.curso c WHERE (c.id = :curso OR :curso is null) AND (t.id = :turma OR :turma is null) ORDER BY c.nome")
    List<Aula> findByCursoAndTurma(@Param("curso") Integer curso, @Param("turma") Integer turma);

}
