package br.org.generation.mandala.repository;

import br.org.generation.mandala.model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemaRepository extends JpaRepository<Tema, Long> {

    public List<Tema> findAllByTituloContainingIgnoreCase(String titulo);
}