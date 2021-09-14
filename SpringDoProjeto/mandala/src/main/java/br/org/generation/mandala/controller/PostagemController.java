package br.org.generation.mandala.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.mandala.model.Postagem;
import br.org.generation.mandala.repository.PostagemRepository;
import br.org.generation.mandala.service.PostagemService;


@RestController
@RequestMapping("/postagem")
@CrossOrigin(origins = "*", allowedHeaders = "*") //SOFEA 
public class PostagemController {
	
	@Autowired 
	private PostagemRepository postagemRepository;
	
	@Autowired 
	private PostagemService postagemService;


	// Listar todas as Postagens da tabela 
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll(){
	return ResponseEntity.ok(postagemRepository.findAll());
	}
	
	//Listar Postagem por ID 
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable long id){
		return postagemRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	//Buscar Postagem por Título
	@GetMapping ("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo)); 
	}
	
	//Criar Postagem
	@PostMapping
	public ResponseEntity<Postagem> postPostagem(@RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
	}
	
	//Atualizar Postagem
	@PutMapping
	public ResponseEntity<Postagem> putPostagem (@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem));
	}
	
	//Deletar Postagem
	@DeleteMapping("/{id}")
	public void deletePostagem(@PathVariable long id) {
		postagemRepository.deleteById(id);
	}
	
	//Curtir Postagem
	@PutMapping("/curtir/{id}")
	public ResponseEntity<Postagem> putCurtirPostagemId (@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(postagemService.curtir(id));	
	}
	
	//Descurtir Postagem
	@PutMapping("/descurtir/{id}")
	public ResponseEntity<Postagem> putDescurtirPostagemId(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(postagemService.descurtir(id));
	}
	
	
	
	
	
	}
