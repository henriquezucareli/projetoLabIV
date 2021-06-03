package br.gov.sp.fatec.projetolabiv.dao;

import br.gov.sp.fatec.projetolabiv.domain.Disciplina;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface DisciplinaDao extends CrudRepository<Disciplina, Integer> {

    @Query("SELECT d FROM Disciplina d WHERE d.id IN :ids")
    List<Disciplina> findAllByIds(@Param("ids") Set<Integer> ids);

    @Query("SELECT d FROM Disciplina d JOIN d.curso c WHERE (d.nome = :nome OR :nome is null) AND (c.id = :curso OR :curso is null) ORDER BY d.nome")
    List<Disciplina> findByNomeAndCurso(@Param("nome") String nome,@Param("curso") Integer curso);
}
