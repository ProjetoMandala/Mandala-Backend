package br.org.generation.mandala.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.mandala.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

		//busca pelo usuiario
		public Optional<Usuario> findByUsuario(String usuario);
		
		//trazendo todos os usuarios
	    public List<Usuario> findAllByNomeContainingIgnoreCase(String nome);
	
	    //busca por nome
	    public Usuario findByNome(String nome);

}
