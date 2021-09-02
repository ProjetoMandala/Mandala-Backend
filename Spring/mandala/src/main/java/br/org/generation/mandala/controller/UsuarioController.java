package br.org.generation.mandala.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.mandala.model.Usuario;
import br.org.generation.mandala.model.UsuarioLogin;
import br.org.generation.mandala.repository.UsuarioRepository;
import br.org.generation.mandala.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin (origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	// traz todos os usuários
	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> findAll (){
		return ResponseEntity.ok(usuarioRepository.findAll());
		
	}
	  // Buscando usuário por ID
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findById (@PathVariable long id){
		return usuarioRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
		
	}
	// buscar usuário pelo nome
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Usuario>> findByNome(@PathVariable String nome) {
		return ResponseEntity.ok(usuarioRepository.findAllByNomeContainingIgnoreCase(nome));
		
	}
	// Criar um usuário
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
		
		Usuario novoUsuario = usuarioService.cadastrarUsuario(usuario);
		
		try {
			
			//se tudo der certo salva no banco
			return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);

			
		} catch (Exception e) {
			// caso contrario retorne o erro
			return ResponseEntity.badRequest().build();		
			
		}
		
	}
	// Editar um usuário
	@PutMapping("/alterar")
	public ResponseEntity<Usuario> editarUsuario(@RequestBody Usuario usuario) {
	Optional<Usuario> updateUsuario = usuarioService.atualizarUsuario(usuario);
		
		try {
			
			//se tudo der certo salva no banco
			return ResponseEntity.ok(updateUsuario.get());
			
		} catch (Exception e) {
			// caso contrario retorne o erro
			return ResponseEntity.badRequest().build();		
			
		}		
	}
	
	//login usuário
	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> autenticandoUsuario(@RequestBody Optional<UsuarioLogin> usuarioLogin){
		
		return usuarioService.loginUsuario(usuarioLogin)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
		
		
	}
	
	//não necessário
//	//Deletar um usuário através do ID
//	@DeleteMapping("/{id}")
//	public void deletarUsuario (@PathVariable long id) {
//		usuarioRepository.deleteById(id);
//	}
	
	
}//fim tudo
