package com.ricardosoftcom.testparadev.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.ricardosoftcom.testparadev.entidades.Tarefa;

public class TarefaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String titulo;
	private String descricao;
	private LocalDateTime prazo;
	private Integer duracao;
	private boolean finalizado;
	private Long idDepartamento;
	private Long idPessoa;
	
	public TarefaDTO() {
	}

	public TarefaDTO(Long id, String titulo, String descricao, LocalDateTime prazo, Integer duracao, boolean finalizado,
			Long idDepartamento, Long idPessoa) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.prazo = prazo;
		this.duracao = duracao;
		this.finalizado = finalizado;
		this.idDepartamento = idDepartamento;
		this.idPessoa = idPessoa;
	}
	
	public TarefaDTO(Tarefa entity) {
		id = entity.getId();
		titulo = entity.getTitulo();
		descricao = entity.getDescricao();
		prazo = entity.getPrazo();
		duracao = entity.getDuracao();
		finalizado = entity.isFinalizado();
		idDepartamento = entity.getDepartamento().getId();
		idPessoa = entity.getPessoa().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getPrazo() {
		return prazo;
	}

	public void setPrazo(LocalDateTime prazo) {
		this.prazo = prazo;
	}

	public Integer getDuracao() {
		return duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public Long getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(Long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}
}