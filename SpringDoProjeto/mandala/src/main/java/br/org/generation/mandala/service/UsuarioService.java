package br.org.generation.mandala.service;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.org.generation.mandala.model.Usuario;
import br.org.generation.mandala.model.UsuarioLogin;
import br.org.generation.mandala.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	//cadastrando usuário
	public Usuario cadastrarUsuario(Usuario usuario){
		
		
		//verificando se o usuário já tem cadastro
		if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
			
			//se o usuario já existe informe 
			throw new  ResponseStatusException( HttpStatus.BAD_REQUEST, "Usuário já existe.", null);

		} else {
			
			//calculando a idade do usuario
			int idade = Period.between(usuario.getDtNascimento(), LocalDate.now()).getYears();
			
			//verificando se o usuário é maior de 18 anos
			if (idade <= 18) {
				
				//informe a ele
				throw new ResponseStatusException(
						HttpStatus.BAD_REQUEST, "Usuário menor de 18 anos", null);
			
			} else {
					
				//criptografando senha
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				
				String senhaEncoder = encoder.encode(usuario.getSenha());
				usuario.setSenha(senhaEncoder);
				
				return usuarioRepository.save(usuario);
				

			}
			
			
		}
		
		
	}
	
	
	
	//atualizar usuário
	public Optional<Usuario> atualizarUsuario(Usuario usuario){
		
		//verificando se o usuario j� est� no banco
			if (usuarioRepository.findById(usuario.getId()).isPresent()) {
				
				//recebendo o email do usuário
				Optional< Usuario > usuarioCad = usuarioRepository.findByUsuario(usuario.getUsuario());
				
				//verificando se o email já esta cadastrado
				if (usuarioCad.isPresent() && (usuarioCad.get().getId() != usuario.getId())) {
					
					throw new ResponseStatusException(
							HttpStatus.BAD_REQUEST, "e-mail do usuario já cadastrado no banco.", null);
				} else {
					
					//calculando a idade do usuario
					int idade = Period.between(usuario.getDtNascimento(), LocalDate.now()).getYears();
					
					//verificando se o usuário é maior de 18 anos
					if (idade <= 18) {
						
						//informe a ele
						throw new ResponseStatusException(
								HttpStatus.BAD_REQUEST, "Usuário menor de 18 anos", null);
					
					} else {
							
						//criptografando senha
						BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
						
						String senhaEncoder = encoder.encode(usuario.getSenha());
						usuario.setSenha(senhaEncoder);
						
						return Optional.of(usuarioRepository.save(usuario));
						

					}

				}
				
				
				
			} else {
				
				throw new ResponseStatusException(
						HttpStatus.NOT_FOUND, "Usuário não encontrado!", null);
	
			}
			
		
	}
			
	
	//realizando o login do usuario
	public Optional<UsuarioLogin> loginUsuario(Optional<UsuarioLogin> usuarioLogin){
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());
		
		if (usuario.isPresent()) {
			
			//comparando a senha que o usuario digitou
			if (encoder.matches(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {
					
				String auth = usuarioLogin.get().getUsuario() + ":" + usuarioLogin.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);//pega o que criptogra e adicional basic
			
				//passando os dados para o usuario login
				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setNome(usuario.get().getNome());
				usuarioLogin.get().setImagem_perfil(usuario.get().getImagem_perfil());
				usuarioLogin.get().setSenha(usuario.get().getSenha());
				usuarioLogin.get().setToken(authHeader);				

				return usuarioLogin;
			
		
			}
			
		} 

			//caso as informações sejam diferentes
			throw new ResponseStatusException(
					HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos!", null);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
