package br.gov.sp.fatec.projetolabiv.controller;

import br.gov.sp.fatec.projetolabiv.controller.payload.DisciplinaPayloadResponse;
import br.gov.sp.fatec.projetolabiv.controller.payload.ProfessorPayloadRequest;
import br.gov.sp.fatec.projetolabiv.controller.payload.ProfessorPayloadResponse;
import br.gov.sp.fatec.projetolabiv.domain.Professor;
import br.gov.sp.fatec.projetolabiv.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService service;

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody @Valid ProfessorPayloadRequest request,
                                    UriComponentsBuilder builder) {
        Integer id = service.save(request.getNome(), request.getTitulacao(), request.getDisciplinas());
        URI uri = builder.path("professores/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProfessorPayloadResponse> update(@PathVariable Integer id, @RequestBody
    @Valid ProfessorPayloadRequest request) {
        if (service.notExists(id)) {
            return ResponseEntity.notFound().build();
        }
        Professor professor = service.update(id, request.getNome(), request.getTitulacao(), request.getDisciplinas());
        ProfessorPayloadResponse response = toPayLoad(professor);
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
    public ResponseEntity<ProfessorPayloadResponse> get(@PathVariable Integer id) {
        Professor professor = service.getById(id).orElse(null);
        if (professor == null) {
            return ResponseEntity.notFound().build();
        }

        ProfessorPayloadResponse response = toPayLoad(professor);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ProfessorPayloadResponse>> search(@RequestParam(required = false) String nome, @RequestParam(required = false) String titulacao){
        List<Professor> professores = service.find(nome, titulacao);
        List<ProfessorPayloadResponse> response = professores.stream().map(this::toPayLoad).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    private ProfessorPayloadResponse toPayLoad(Professor professor) {
        Set<DisciplinaPayloadResponse> disciplinasResponse = professor.getDisciplinas().stream().map(disciplina -> {
            return DisciplinaPayloadResponse.builder().id(disciplina.getId()).nome(disciplina.getNome()).build();
        }).collect(Collectors.toSet());

        return ProfessorPayloadResponse.builder().id(professor.getId()).nome(professor.getNome()).titulacao(
                professor.getTitulacao()).disciplinas(disciplinasResponse).build();
    }
}
