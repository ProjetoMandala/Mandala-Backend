package br.org.generation.mandala.controller;

import br.org.generation.mandala.model.Tema;
import br.org.generation.mandala.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tema")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemaController {

    //importando a class TemaRepository
    @Autowired
    private TemaRepository repository;

    // Buscando todos os campos da tabela
    @GetMapping
    public ResponseEntity<List<Tema>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    // Buscando tema por ID
    @GetMapping("/{id}")
    public ResponseEntity<Tema> findById(@PathVariable long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Buscando tema por titulo
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Tema>> findByTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
    }

    // Criar tema
    @PostMapping
    public ResponseEntity<Tema> criarTema(@RequestBody Tema tema) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
    }

    // Editar tema
    @PutMapping
    public ResponseEntity<Tema> editarTema(@RequestBody Tema tema) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(tema));
    }

    // Deletar tema
    @DeleteMapping("/{id}")
    public void deletarTema(@PathVariable long id) {
        repository.deleteById(id);
    }

}
