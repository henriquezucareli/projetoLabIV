package br.gov.sp.fatec.projetolabiv.service;

import br.gov.sp.fatec.projetolabiv.dao.CursoDao;
import br.gov.sp.fatec.projetolabiv.domain.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoDao dao;

    public Integer save(final String pNome, final String pPeridiocidade, final String pDescricao) {
        Curso curso = new Curso();
        curso.setNome(pNome);
        curso.setPeriodicidade(pPeridiocidade);
        curso.setDescricao(pDescricao);

        return dao.save(curso).getId();
    }

    public boolean notExists(Integer pId) {
        return !dao.existsById(pId);
    }

    public Curso update(Integer pId, final String pNome, final String pPeridiocidade, final String pDescricao) {
        Curso curso = dao.findById(pId).get();
        curso.setNome(pNome);
        curso.setPeriodicidade(pPeridiocidade);
        curso.setDescricao(pDescricao);

        return dao.save(curso);
    }


    public void delete(Integer pId) {
        dao.deleteById(pId);
    }

    public Optional<Curso> getById(Integer pId) {
        return dao.findById(pId);
    }

    public List<Curso> find(String pNome) {
        return dao.findByNomeOrderByNome(pNome);
    }
}
