package br.gov.sp.fatec.projetolabiv.controller;

import br.gov.sp.fatec.projetolabiv.controller.payload.CursoPayloadRequest;
import br.gov.sp.fatec.projetolabiv.controller.payload.CursoPayloadResponse;
import br.gov.sp.fatec.projetolabiv.domain.Curso;
import br.gov.sp.fatec.projetolabiv.service.CursoService;
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
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/cursos")
public class CursoController {

    @Autowired
    private CursoService service;

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody @Valid CursoPayloadRequest request,
                                    UriComponentsBuilder builder) {
        Integer id = service.save(request.getNome(), request.getPeriodicidade(), request.getDescricao());
        URI uri = builder.path("cursos/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CursoPayloadResponse> update(@PathVariable Integer id, @RequestBody
    @Valid CursoPayloadRequest request) {
        if (service.notExists(id)) {
            return ResponseEntity.notFound().build();
        }
        Curso curso = service.update(id, request.getNome(), request.getPeriodicidade(), request.getDescricao());
        CursoPayloadResponse response = toPayLoad(curso);
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
    public ResponseEntity<CursoPayloadResponse> get(@PathVariable Integer id) {
        Curso curso = service.getById(id).orElse(null);
        if (curso == null) {
            return ResponseEntity.notFound().build();
        }

        CursoPayloadResponse response = toPayLoad(curso);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CursoPayloadResponse>> search(@RequestParam(required = false) String nome){
        List<Curso> cursos = service.find(nome);
        List<CursoPayloadResponse> response = cursos.stream().map(this::toPayLoad).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    private CursoPayloadResponse toPayLoad(Curso curso) {
        return CursoPayloadResponse.builder().id(curso.getId()).nome(curso.getNome()).descricao(
                curso.getDescricao()).periodicidade(curso.getPeriodicidade()).build();
    }
}
