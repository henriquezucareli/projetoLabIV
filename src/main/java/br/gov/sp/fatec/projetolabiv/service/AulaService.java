package br.gov.sp.fatec.projetolabiv.service;

import br.gov.sp.fatec.projetolabiv.dao.*;
import br.gov.sp.fatec.projetolabiv.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AulaService {

    @Autowired
    private AulaDao dao;

    @Autowired
    private TurmaDao turmaDao;

    @Autowired
    private DisciplinaDao disciplinaDao;

    @Autowired
    private ProfessorDao professorDao;

    @Autowired
    private CursoDao cursoDao;


    public Integer save(Integer pTurma, Integer pDisciplina, Integer pCurso, Integer pProfessor) {

        Turma turma = turmaDao.findById(pTurma).orElseThrow();
        Disciplina disciplina = disciplinaDao.findById(pDisciplina).orElseThrow();
        Professor professor = professorDao.findById(pProfessor).orElseThrow();
        Curso curso = cursoDao.findById(pCurso).orElseThrow();

        Aula aula = new Aula();
        aula.setTurma(turma);
        aula.setDisciplina(disciplina);
        aula.setProfessor(professor);
        aula.setCurso(curso);

        return dao.save(aula).getId();
    }

    public boolean notExists(Integer pId) {
        return !dao.existsById(pId);
    }


    public Aula update(Integer pId, Integer pTurma, Integer pDisciplina, Integer pCurso, Integer pProfessor) {

        Turma turma = turmaDao.findById(pTurma).orElseThrow();
        Disciplina disciplina = disciplinaDao.findById(pDisciplina).orElseThrow();
        Professor professor = professorDao.findById(pProfessor).orElseThrow();
        Curso curso = cursoDao.findById(pCurso).orElseThrow();

        Aula aula = dao.findById(pId).get();
        aula.setTurma(turma);
        aula.setDisciplina(disciplina);
        aula.setProfessor(professor);
        aula.setCurso(curso);

        return dao.save(aula);

    }

    public void delete(Integer pId) {
        dao.deleteById(pId);
    }

    public Optional<Aula> getById(Integer pId) {
        return dao.findById(pId);
    }


    public List<Aula> find(Integer curso, Integer turma) {

        return dao.findByCursoAndTurma(curso, turma);
    }
}
