package br.org.generation.mandala.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table (name = "tb_usuario")
public class Usuario {
	
	
	//atributos
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull (message = "O atributo nome precisa ser preenchido")
	@Size (min = 2, max = 255)
	private String nome;
	
	@NotNull (message = "O atributo data de nascimento precisa ser preenchido")
	@Column(name = "dt_nascimento")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dtNascimento; 
	
	private String imagem_perfil;
	
	private String tipo;
	

	@NotNull (message = "O atributo gênero precisa ser preenchido")
	@Size (min = 2, max = 50)
	private String genero;
	
	@Size (min = 2, max = 160)
	private String biografia;
	
	@NotNull (message = "O atributo email precisa ser preenchido")
	@Size (min = 2, max = 100)
	@Email
	private String usuario;
	
	@NotNull (message = "O atributo senha precisa ser preenchido")
	@Size (min = 6)
	private String  senha;

	// relacionamento com a tabela postagem
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)//só irá afetar a postagem caso de remover o usuario
	@JsonIgnoreProperties({"usuario","tema"})
	private List<Postagem> postagem;
	
	//get e set
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public LocalDate getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(LocalDate dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}

	public String getImagem_perfil() {
		return imagem_perfil;
	}

	public void setImagem_perfil(String imagem_perfil) {
		this.imagem_perfil = imagem_perfil;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
		

}
