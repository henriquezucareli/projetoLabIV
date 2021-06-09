package br.gov.sp.fatec.projetolabiv.service;

import br.gov.sp.fatec.projetolabiv.dao.AlunoDao;
import br.gov.sp.fatec.projetolabiv.dao.CursoDao;
import br.gov.sp.fatec.projetolabiv.dao.TurmaDao;
import br.gov.sp.fatec.projetolabiv.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class TurmaService {

    @Autowired
    private TurmaDao dao;
    @Autowired
    private CursoDao cursoDao;
    @Autowired
    private AlunoDao alunoDao;

    public Integer save(String pDescricao, Set<Integer> pAlunos, Integer pCurso) {
        Curso curso = cursoDao.findById(pCurso).orElseThrow();

        Turma turma = new Turma();
        turma.setDescricao(pDescricao);
        turma.setCurso(curso);

        if (pAlunos != null) {
            List<Aluno> alunos = alunoDao.findAllByIds(pAlunos);
            turma.addAluno(alunos);
        }
        return dao.save(turma).getId();
    }

    public boolean notExists(Integer pId) {
        return !dao.existsById(pId);
    }


    public Turma update(Integer pId, String pDescricao, Integer pCurso, Set<Integer> pAlunos) {
        Curso curso = cursoDao.findById(pCurso).orElseThrow();

        Turma turma = dao.findById(pId).get();
        turma.setDescricao(pDescricao);
        turma.setCurso(curso);
        turma.clearAlunos();

        if (pAlunos != null) {
            List<Aluno> alunos = alunoDao.findAllByIds(pAlunos);
            turma.addAluno(alunos);
        }
        return dao.save(turma);
    }

    public void delete(Integer pId) {
        dao.deleteById(pId);
    }

    public Optional<Turma> getById(Integer pId) {
        return dao.findById(pId);
    }


    public List<Turma> find(Integer pCurso) {
        return dao.findByCurso(pCurso);
    }
}
