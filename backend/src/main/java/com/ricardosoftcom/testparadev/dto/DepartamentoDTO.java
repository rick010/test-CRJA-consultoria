package com.ricardosoftcom.testparadev.dto;

import java.io.Serializable;

import com.ricardosoftcom.testparadev.entidades.Departamento;

public class DepartamentoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String titulo;
	
	private Long quantidadePessoas = 0L;
	
	private Long quantidadeTarefas = 0L;
	
	private Long QuantidadeTarefasFinalizadas = 0L;
	private Long QuantidadeTarefasNãoFinalizadas = 0L;
	
	public DepartamentoDTO() {
	}

	public DepartamentoDTO(Long id, String titulo) {
		this.id = id;
		this.titulo = titulo;
	}
	
	public DepartamentoDTO(Departamento entity) {
		id = entity.getId();
		titulo = entity.getTitulo();
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

	public Long getQuantidadePessoas() {
		return quantidadePessoas;
	}

	public void setQuantidadePessoas(Long quantidadePessoas) {
		this.quantidadePessoas = quantidadePessoas;
	}

	public Long getQuantidadeTarefas() {
		return quantidadeTarefas;
	}

	public void setQuantidadeTarefas(Long quantidadeTarefas) {
		this.quantidadeTarefas = quantidadeTarefas;
	}

	public Long getQuantidadeTarefasFinalizadas() {
		return QuantidadeTarefasFinalizadas;
	}

	public void setQuantidadeTarefasFinalizadas(Long quantidadeTarefasFinalizadas) {
		QuantidadeTarefasFinalizadas = quantidadeTarefasFinalizadas;
	}

	public Long getQuantidadeTarefasNãoFinalizadas() {
		return QuantidadeTarefasNãoFinalizadas;
	}

	public void setQuantidadeTarefasNãoFinalizadas(Long quantidadeTarefasNãoFinalizadas) {
		QuantidadeTarefasNãoFinalizadas = quantidadeTarefasNãoFinalizadas;
	}
	
	
}