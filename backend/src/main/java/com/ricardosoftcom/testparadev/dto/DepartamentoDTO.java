package com.ricardosoftcom.testparadev.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.ricardosoftcom.testparadev.entidades.Departamento;
import com.ricardosoftcom.testparadev.entidades.Pessoa;
import com.ricardosoftcom.testparadev.entidades.Tarefa;

public class DepartamentoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String titulo;
	
	private List<PessoaDTO> pessoas = new ArrayList<>();
	
	private List<TarefaDTO> tarefas = new ArrayList<>();
	
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
	
	public DepartamentoDTO(Departamento entity, Set<Pessoa> pessoas, List<Tarefa> tarefas) {
		this(entity);
		pessoas.forEach(p -> this.pessoas.add(new PessoaDTO(p)));
		tarefas.forEach(t -> this.tarefas.add(new TarefaDTO(t)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<PessoaDTO> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<PessoaDTO> pessoas) {
		this.pessoas = pessoas;
	}

	public String getTitulo() {
		return titulo;
	}

	public List<TarefaDTO> getTarefas() {
		return tarefas;
	}
}