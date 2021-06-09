package br.gov.sp.fatec.projetolabiv.controller;

import br.gov.sp.fatec.projetolabiv.controller.payload.*;
import br.gov.sp.fatec.projetolabiv.domain.Disciplina;
import br.gov.sp.fatec.projetolabiv.domain.Turma;
import br.gov.sp.fatec.projetolabiv.service.DisciplinaService;
import br.gov.sp.fatec.projetolabiv.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/turmas")
public class TurmaController {

    @Autowired
    private TurmaService service;

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody @Valid TurmaPayloadRequest request,
                                    UriComponentsBuilder builder) {
        Integer id = service.save(request.getDescricao(), request.getAlunos(), request.getCurso());
        URI uri = builder.path("turmas/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<TurmaPayloadResponse> update(@PathVariable Integer id, @RequestBody
    @Valid TurmaPayloadRequest request) {
        if (service.notExists(id)) {
            return ResponseEntity.notFound().build();
        }
        Turma turma  = service.update(id, request.getDescricao(), request.getCurso(), request.getAlunos());
        TurmaPayloadResponse response = toPayLoad(turma);
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
    public ResponseEntity<TurmaPayloadResponse> get(@PathVariable Integer id) {
        Turma turma = service.getById(id).orElse(null);
        if (turma == null) {
            return ResponseEntity.notFound().build();
        }
        TurmaPayloadResponse response = toPayLoad(turma);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TurmaPayloadResponse>> search(@RequestParam(required = false) Integer curso) {
        List<Turma> turmas = service.find(curso);
        List<TurmaPayloadResponse> response =
                turmas.stream().map(this::toPayLoad).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    private TurmaPayloadResponse toPayLoad(Turma turma) {
        Set<AlunoPayloadResponse> alunosResponse = turma.getAlunos().stream().map(aluno -> {
            return AlunoPayloadResponse.builder().id(aluno.getId()).nome(aluno.getNome()).build();
        }).collect(Collectors.toSet());

        CursoPayloadResponse curso =
                CursoPayloadResponse.builder().id(turma.getCurso().getId()).nome(turma.getCurso().getNome())
                        .build();

        return TurmaPayloadResponse.builder().id(turma.getId()).curso(curso).alunos(alunosResponse).build();
    }
}
