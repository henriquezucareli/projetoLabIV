package br.gov.sp.fatec.projetolabiv.service;

import br.gov.sp.fatec.projetolabiv.dao.CursoDao;
import br.gov.sp.fatec.projetolabiv.dao.DisciplinaDao;
import br.gov.sp.fatec.projetolabiv.dao.ProfessorDao;
import br.gov.sp.fatec.projetolabiv.domain.Curso;
import br.gov.sp.fatec.projetolabiv.domain.Disciplina;
import br.gov.sp.fatec.projetolabiv.domain.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaDao dao;
    @Autowired
    private ProfessorDao professorDao;
    @Autowired
    private CursoDao cursoDao;

    public Integer save(final String pNome, final Integer pCargaHoraria, final String pEmenta, final Integer pCurso,
                        Set<Integer> pProfessores) {
        Curso curso = cursoDao.findById(pCurso).orElseThrow();

        Disciplina disciplina = new Disciplina();
        disciplina.setNome(pNome);
        disciplina.setCargaHoraria(pCargaHoraria);
        disciplina.setEmenta(pEmenta);
        disciplina.setCurso(curso);

        if (pProfessores != null) {
            List<Professor> professores = professorDao.findAllByIds(pProfessores);
            disciplina.addProfessor(professores);
        }
        return dao.save(disciplina).getId();
    }

    public boolean notExists(Integer pId) {
        return !dao.existsById(pId);
    }

    public Disciplina update(Integer pId, final String pNome, final Integer pCargaHoraria, final String pEmenta,
                             final Integer pCurso, Set<Integer> pProfessores) {
        Curso curso = cursoDao.findById(pCurso).orElseThrow();

        Disciplina disciplina = dao.findById(pId).get();
        disciplina.setNome(pNome);
        disciplina.setCargaHoraria(pCargaHoraria);
        disciplina.setEmenta(pEmenta);
        disciplina.setCurso(curso);
        disciplina.clearProfessores();

        if (pProfessores != null) {
            List<Professor> professores = professorDao.findAllByIds(pProfessores);
            disciplina.addProfessor(professores);
        }
        return dao.save(disciplina);
    }

    public void delete(Integer pId) {
        dao.deleteById(pId);
    }

    public Optional<Disciplina> getById(Integer pId) {
        return dao.findById(pId);
    }

    public List<Disciplina> find(String pNome, Integer pCurso) {
        return dao.findByNomeAndCurso(pNome, pCurso);
    }
}
