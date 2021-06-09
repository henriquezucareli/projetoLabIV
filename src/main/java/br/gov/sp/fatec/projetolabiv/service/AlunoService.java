package br.gov.sp.fatec.projetolabiv.service;

import br.gov.sp.fatec.projetolabiv.dao.AlunoDao;
import br.gov.sp.fatec.projetolabiv.dao.TurmaDao;
import br.gov.sp.fatec.projetolabiv.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AlunoService {

    @Autowired
    private AlunoDao dao;
    @Autowired
    private TurmaDao turmaDao;

    public Integer save(String pNome, Integer pTurma) {
        Turma turma = turmaDao.findById(pTurma).orElseThrow();

        Aluno aluno = new Aluno();
        aluno.setNome(pNome);
        aluno.setTurma(turma);

        return dao.save(aluno).getId();
    }

    public boolean notExists(Integer pId) {
        return !dao.existsById(pId);
    }


    public Aluno update(Integer pId, String pNome, Integer pTurma) {
        Turma turma = turmaDao.findById(pTurma).orElseThrow();

        Aluno aluno = dao.findById(pId).get();
        aluno.setNome(pNome);
        aluno.setTurma(turma);

        return dao.save(aluno);
    }

    public void delete(Integer pId) {
        dao.deleteById(pId);
    }

    public Optional<Aluno> getById(Integer pId) {
        return dao.findById(pId);
    }

    public List<Aluno> find(String pNome, Integer pTurma) {
        return dao.findByNomeAndTurma(pNome, pTurma);
    }
}
