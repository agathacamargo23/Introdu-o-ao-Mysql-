package org.generation.lojagames.Lojagames.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name= "tb_categoria")
public class CategoriaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	
	@NotNull
	@Size(min = 5 , max = 30)
	private String genero;
	
	@NotNull
	@Size(min = 5 , max = 20)
	private String tipo;
	
	@NotNull
	@Size(min = 5 , max = 50)
	private String nome;
	
	@NotNull
	@Size(min = 3 , max = 6)
	private double versao;
	
	@NotNull
	@Size(min = 4 , max = 10)
	private double preco;
	
	@NotNull
	@Size(min = 5 , max = 200)
    private String descricao;
	
	@ManyToOne
	@JsonIgnoreProperties("tb_categoria")
	private ProdutoModel produto;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getVersao() {
		return versao;
	}

	public void setVersao(double versao) {
		this.versao = versao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	
	
	}

	public ProdutoModel getProduto() {
		return produto;
	}

	public void setProduto(ProdutoModel produto) {
		this.produto = produto;
	}
}