package br.gov.sp.fatec.projetolabiv.dao;

import br.gov.sp.fatec.projetolabiv.domain.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaDao extends JpaRepository<Disciplina,Integer> {
}
