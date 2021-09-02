package br.org.generation.mandala.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name = "tb_tema")
public class Tema {
	
	//criando os atributos da tabela
	//chave primaria
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message = "O campo titulo é obrigatório")
	@Size(min = 2, max = 100, message = "O campo titulo deve conter no minimo 5 e no maximo 100 caracteres.")
	private String titulo;
	
	@Size(min = 2, max = 20, message = "O campo palavraChave deve conter no minimo 5 e no maximo 20 caracteres.")
	private String palavraChave;

	@Size(min = 2, max = 255, message = "O campo imagem deve conter no minimo 10 e no maximo 255 caracteres.")
	private String imagem;
	
	@NotNull(message = "O campo descrição é obrigatório")
	@Size(min = 2, max = 255, message = "O campo descrição deve conter no minimo 10 e no maximo 255 caracteres.")
	private String descricao;
	
	//chave FK
	@OneToMany(mappedBy = "tema", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("tema")
	private List<Postagem> postagem;	
	

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}

	//get e set
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getPalavraChave() {
		return palavraChave;
	}

	public void setPalavraChave(String palavraChave) {
		this.palavraChave = palavraChave;
	}
	
	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	

}
