package br.gov.sp.fatec.projetolabiv.controller;

import br.gov.sp.fatec.projetolabiv.controller.payload.*;
import br.gov.sp.fatec.projetolabiv.domain.Aluno;
import br.gov.sp.fatec.projetolabiv.domain.Aula;
import br.gov.sp.fatec.projetolabiv.service.AulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/aulas")
public class AulaController {

    @Autowired
    private AulaService service;

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody @Valid AulaPayloadRequest request,
                                    UriComponentsBuilder builder) {
        Integer id = service.save(request.getTurma(), request.getDisciplina(), request.getCurso(), request.getProfessor());
        URI uri = builder.path("aulas/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<AulaPayloadResponse> update(@PathVariable Integer id, @RequestBody
    @Valid AulaPayloadRequest request) {
        if (service.notExists(id)) {
            return ResponseEntity.notFound().build();
        }
        Aula aula = service.update(id, request.getTurma(), request.getDisciplina(), request.getCurso(), request.getProfessor());
        AulaPayloadResponse response = toPayLoad(aula);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (service.notExists(id)) {
            return ResponseEntity.notFound().build();
        }
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AulaPayloadResponse> get(@PathVariable Integer id) {
        Aula aula = service.getById(id).orElse(null);
        if (aula == null) {
            return ResponseEntity.notFound().build();
        }
        AulaPayloadResponse response = toPayLoad(aula);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AulaPayloadResponse>> search(@RequestParam(required = false) Integer curso,
                                                             @RequestParam(required = false) Integer turma) {
        List<Aula> aulas = service.find(curso, turma);
        List<AulaPayloadResponse> response =
                aulas.stream().map(this::toPayLoad).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    private AulaPayloadResponse toPayLoad(Aula aula) {
        TurmaPayloadResponse turma =
                TurmaPayloadResponse.builder().id(aula.getTurma().getId()).descricao(aula.getTurma().getDescricao())
                        .build();
        DisciplinaPayloadResponse disciplina =
                DisciplinaPayloadResponse.builder().id(aula.getDisciplina().getId()).nome(aula.getDisciplina().getNome())
                        .build();
        ProfessorPayloadResponse professor = ProfessorPayloadResponse.builder().id(aula.getProfessor().getId()).nome(aula.getProfessor().getNome()).build();

        CursoPayloadResponse curso = CursoPayloadResponse.builder().id(aula.getCurso().getId()).nome(aula.getCurso().getNome()).build();


        return AulaPayloadResponse.builder().id(aula.getId()).turma(turma).disciplina(disciplina).professor(professor).curso(curso).build();
    }
}
