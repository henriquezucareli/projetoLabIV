package br.gov.sp.fatec.projetolabiv.service;

import br.gov.sp.fatec.projetolabiv.dao.DisciplinaDao;
import br.gov.sp.fatec.projetolabiv.dao.ProfessorDao;
import br.gov.sp.fatec.projetolabiv.domain.Disciplina;
import br.gov.sp.fatec.projetolabiv.domain.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorDao dao;
    @Autowired
    private DisciplinaDao disciplinaDao;


    public Integer save(final String pNome, final String pTitulacao, final Set<Integer> pDisciplinas) {
        Professor professor = new Professor();
        professor.setNome(pNome);
        professor.setTitulacao(pTitulacao);

        if (pDisciplinas != null) {
            List<Disciplina> disciplinas = disciplinaDao.findAllByIds(pDisciplinas);
            professor.addDisciplina(disciplinas);
        }
        return dao.save(professor).getId();
    }

    public boolean notExists(Integer pId) {
        return !dao.existsById(pId);
    }

    public Professor update(Integer pId, String pNome, String pTitulacao, Set<Integer> pDisciplinas) {
        Professor professor = dao.findById(pId).get();
        professor.setNome(pNome);
        professor.setTitulacao(pTitulacao);
        professor.clearDisciplinas();

        if (pDisciplinas != null) {
            List<Disciplina> disciplinas = disciplinaDao.findAllByIds(pDisciplinas);
            professor.addDisciplina(disciplinas);
        }
        return dao.save(professor);
    }


    public void delete(Integer pId) {
        dao.deleteById(pId);
    }

    public Optional<Professor> getById(Integer pId) {
        return dao.findById(pId);
    }

    public List<Professor> find(String pNome, String pTitulacao) {
        return dao.findByNomeAndTitulacaoOrderByNome(pNome, pTitulacao);
    }
}
