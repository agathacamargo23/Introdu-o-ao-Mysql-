package org.generation.lojagames.Lojagames.model;

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
@Table(name = "tb_cliente")
public class ClienteModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	
	@NotNull
	@Size(min = 2, message = "Não deixar o campo vazio")
	private String nome;
	
	@NotNull
	@Size(min = 2, message = "O usuário não pode deixar vazio")
	private String usuario;
	
	@NotNull
	@Size(min = 6, message = "A senha não pode ser menor que seis caracteres")
	private String senha;
	
	
	@OneToMany(mappedBy =  "cliente", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("cliente")
	private List<ProdutoModel> produto;


	public long getId() {
		return Id;
	}


	public void setId(long id) {
		Id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
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


	public List<ProdutoModel> getProduto() {
		return produto;
	}


	public void setProduto(List<ProdutoModel> produto) {
		this.produto = produto;
	}
	
	
	
	

	
}
