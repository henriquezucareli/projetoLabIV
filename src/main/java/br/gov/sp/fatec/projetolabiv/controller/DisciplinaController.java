package br.gov.sp.fatec.projetolabiv.controller;

import br.gov.sp.fatec.projetolabiv.controller.payload.CursoPayloadResponse;
import br.gov.sp.fatec.projetolabiv.controller.payload.DisciplinaPayloadRequest;
import br.gov.sp.fatec.projetolabiv.controller.payload.DisciplinaPayloadResponse;
import br.gov.sp.fatec.projetolabiv.controller.payload.ProfessorPayloadResponse;
import br.gov.sp.fatec.projetolabiv.domain.Disciplina;
import br.gov.sp.fatec.projetolabiv.service.DisciplinaService;
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
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService service;

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody @Valid DisciplinaPayloadRequest request,
                                    UriComponentsBuilder builder) {
        Integer id = service.save(request.getNome(), request.getCargaHoraria(), request.getEmenta(), request.getCurso(),
                request.getProfessores());
        URI uri = builder.path("disciplinas/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<DisciplinaPayloadResponse> update(@PathVariable Integer id, @RequestBody
    @Valid DisciplinaPayloadRequest request) {
        if (service.notExists(id)) {
            return ResponseEntity.notFound().build();
        }
        Disciplina disciplina = service.update(id, request.getNome(), request.getCargaHoraria(), request.getEmenta(),
                request.getCurso(), request.getProfessores());
        DisciplinaPayloadResponse response = toPayLoad(disciplina);
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
    public ResponseEntity<DisciplinaPayloadResponse> get(@PathVariable Integer id) {
        Disciplina disciplina = service.getById(id).orElse(null);
        if (disciplina == null) {
            return ResponseEntity.notFound().build();
        }
        DisciplinaPayloadResponse response = toPayLoad(disciplina);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DisciplinaPayloadResponse>> search(@RequestParam(required = false) String nome,
                                                                  @RequestParam(required = false) Integer curso) {
        List<Disciplina> disciplinas = service.find(nome, curso);
        List<DisciplinaPayloadResponse> response =
                disciplinas.stream().map(this::toPayLoad).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    private DisciplinaPayloadResponse toPayLoad(Disciplina disciplina) {
        Set<ProfessorPayloadResponse> professoresResponse = disciplina.getProfessores().stream().map(professor -> {
            return ProfessorPayloadResponse.builder().id(professor.getId()).nome(professor.getNome()).build();
        }).collect(Collectors.toSet());

        CursoPayloadResponse curso =
                CursoPayloadResponse.builder().id(disciplina.getCurso().getId()).nome(disciplina.getCurso().getNome())
                        .build();

        return DisciplinaPayloadResponse.builder().id(disciplina.getId()).nome(disciplina.getNome()).cargaHoraria(
                disciplina.getCargaHoraria()).curso(curso).professores(professoresResponse).build();
    }
}
