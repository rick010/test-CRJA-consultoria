package com.ricardosoftcom.testparadev.dto;

import java.io.Serializable;

import com.ricardosoftcom.testparadev.entidades.Pessoa;

public class PessoaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private Long idDepartamento;
	private Long horasTrabalhadas;
	private Long mediaHorasTarefas;
	
	public PessoaDTO() {
	}

	public PessoaDTO(Long id, String nome, Long idDepartamento) {
		this.id = id;
		this.nome = nome;
		this.idDepartamento = idDepartamento;
	}
	
	public PessoaDTO(Pessoa entity) {
		id = entity.getId();
		nome = entity.getNome();
		idDepartamento = entity.getDepartamento().getId();
	}
	
	public PessoaDTO(Pessoa entity, Long horasTrabalhadas) {
		this(entity);
		this.horasTrabalhadas = horasTrabalhadas;
	}
	
	public PessoaDTO(Pessoa entity, Long horasTrabalhadas, Long mediaHorasTarefas) {
		this(entity);
		this.mediaHorasTarefas = mediaHorasTarefas;
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
	
	public Long getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(Long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public Long getHorasTrabalhadas() {
		return horasTrabalhadas;
	}

	public void setHorasTrabalhadas(Long horasTrabalhadas) {
		
		this.horasTrabalhadas = horasTrabalhadas;
	}

	public Long getMediaHorasTarefas() {
		return mediaHorasTarefas;
	}

	public void setMediaHorasTarefas(Long mediaHorasTarefas) {
		this.mediaHorasTarefas = mediaHorasTarefas;
	}
	
	
	
}