package br.gov.sp.fatec.projetolabiv.dao;

import br.gov.sp.fatec.projetolabiv.domain.Professor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProfessorDao extends CrudRepository<Professor, Integer> {
    @Query("SELECT p FROM Professor p WHERE (p.nome = :nome OR :nome is null) AND (p.titulacao = :titulacao OR :titulacao is null) ORDER BY p.nome")
    List<Professor> findByNomeAndTitulacaoOrderByNome(@Param("nome") String nome, @Param("titulacao") String titulacao);

    @Query("SELECT p FROM Professor p WHERE p.id IN :ids")
    List<Professor> findAllByIds(@Param("ids") Set<Integer> ids);
}
