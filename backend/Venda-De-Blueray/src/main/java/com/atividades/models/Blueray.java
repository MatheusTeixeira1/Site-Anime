package com.atividades.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "blueray")
public class Blueray {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "Nome é obrigatório")
	@NotNull(message = "Nome não deve ser nulo")
	private String nome;
	private String descricao;
	private Double preco;
	private String imagem;
	private Integer season;
	
	
	public Blueray() {
		super();
	}
	public Blueray(Long id ,String nome, String quantidade, Double preco, String imagem, Integer season) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = quantidade;
		this.preco = preco;
		this.imagem = imagem;
		this.season = season;
	}
	public Blueray(String nome, String quantidade, Double preco, String imagem, Integer season) {
		super();
		this.nome = nome;
		this.descricao = quantidade;
		this.preco = preco;
		this.imagem = imagem;
		this.season = season;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String quantidade) {
		this.descricao = quantidade;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public Integer getSeason() {
		return season;
	}
	public void setSeason(Integer season) {
		this.season = season;
	}
}
