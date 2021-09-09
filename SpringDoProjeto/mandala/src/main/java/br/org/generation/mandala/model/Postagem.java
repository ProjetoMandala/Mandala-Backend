package br.org.generation.mandala.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Postagem model
@Entity
@Table (name = "tb_postagem")
public class Postagem {
	
	//atributos da tabela
	@Id //Chave Primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Auto incremento
	private long id;

	@NotNull(message = "É obrigatório atribuir um título")
	@Size (max = 100)
    private String titulo;
	
	@NotNull(message = "É obrigatório preencher o campo de Texto")
	@Size (max = 1000)
	private String texto;
	
	@Size(max = 255)
	private String imagem;
	
	@Size(max = 255)
	private String video;
	
	@PositiveOrZero
	private int curtida;
	
	//chave estrangeira Tema e Usuario
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;
	
	
	//get e set
	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	
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

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public int getCurtida() {
		return curtida;
	}

	public void setCurtida(int curtida) {
		this.curtida = curtida;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
}
