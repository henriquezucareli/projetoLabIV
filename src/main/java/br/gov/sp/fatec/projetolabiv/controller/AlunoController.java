package br.gov.sp.fatec.projetolabiv.controller;

import br.gov.sp.fatec.projetolabiv.controller.payload.AlunoPayloadRequest;
import br.gov.sp.fatec.projetolabiv.controller.payload.AlunoPayloadResponse;
import br.gov.sp.fatec.projetolabiv.controller.payload.TurmaPayloadResponse;
import br.gov.sp.fatec.projetolabiv.domain.Aluno;
import br.gov.sp.fatec.projetolabiv.service.AlunoService;
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
@RequestMapping(path = "/alunos")
public class AlunoController {

    @Autowired
    private AlunoService service;

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody @Valid AlunoPayloadRequest request,
                                    UriComponentsBuilder builder) {
        Integer id = service.save(request.getNome(), request.getTurma());
        URI uri = builder.path("alunos/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<AlunoPayloadResponse> update(@PathVariable Integer id, @RequestBody
    @Valid AlunoPayloadRequest request) {
        if (service.notExists(id)) {
            return ResponseEntity.notFound().build();
        }
        Aluno aluno = service.update(id, request.getNome(), request.getTurma());
        AlunoPayloadResponse response = toPayLoad(aluno);
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
    public ResponseEntity<AlunoPayloadResponse> get(@PathVariable Integer id) {
        Aluno aluno = service.getById(id).orElse(null);
        if (aluno == null) {
            return ResponseEntity.notFound().build();
        }
        AlunoPayloadResponse response = toPayLoad(aluno);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AlunoPayloadResponse>> search(@RequestParam(required = false) String nome,
                                                                  @RequestParam(required = false) Integer turma) {
        List<Aluno> alunos = service.find(nome, turma);
        List<AlunoPayloadResponse> response =
                alunos.stream().map(this::toPayLoad).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    private AlunoPayloadResponse toPayLoad(Aluno aluno) {
        TurmaPayloadResponse turma =
                TurmaPayloadResponse.builder().id(aluno.getTurma().getId()).descricao(aluno.getTurma().getDescricao())
                        .build();

        return AlunoPayloadResponse.builder().id(aluno.getId()).nome(aluno.getNome()).turma(turma).build();
    }
}
