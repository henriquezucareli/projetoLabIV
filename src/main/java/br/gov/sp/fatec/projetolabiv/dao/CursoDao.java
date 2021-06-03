package br.gov.sp.fatec.projetolabiv.dao;

import br.gov.sp.fatec.projetolabiv.domain.Curso;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoDao extends CrudRepository<Curso, Integer> {

    @Query("SELECT p FROM Curso p WHERE (p.nome = :nome OR :nome is null) ORDER BY p.nome")
    List<Curso> findByNomeOrderByNome(@Param("nome") String nome);
}
