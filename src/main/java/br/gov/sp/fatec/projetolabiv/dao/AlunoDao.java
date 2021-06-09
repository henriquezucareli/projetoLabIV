package br.gov.sp.fatec.projetolabiv.dao;

import br.gov.sp.fatec.projetolabiv.domain.Aluno;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface AlunoDao extends CrudRepository<Aluno, Integer> {

    @Query("SELECT a FROM Aluno a JOIN a.turma t WHERE (a.nome = :nome OR :nome is null) AND (t.id = :turma OR :turma is null) ORDER BY a.nome")
    List<Aluno> findByNomeAndTurma(@Param("nome") String nome, @Param("turma") Integer turma);

    @Query("SELECT a FROM Aluno a WHERE a.id IN :ids")
    List<Aluno> findAllByIds(@Param("ids") Set<Integer> ids);

}
