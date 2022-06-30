package com.ricardosoftcom.testparadev.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_pessoa")
public class Pessoa implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "id_departamento")
	private Departamento pessoaDepartamento;
	
	@OneToMany(mappedBy = "pessoa", cascade=CascadeType.ALL)
	private Set<Tarefa> tarefas = new HashSet<>();
	
	public Pessoa() {
	}

	public Pessoa(Long id, String nome, Departamento pessoaDepartamento) {
		this.id = id;
		this.nome = nome;
		this.pessoaDepartamento = pessoaDepartamento;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Departamento getPessoaDepartamento() {
		return pessoaDepartamento;
	}
	public void setPessoaDepartamento(Departamento pessoaDepartamento) {
		this.pessoaDepartamento = pessoaDepartamento;
	}

	public Set<Tarefa> getTarefas() {
		return tarefas;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(id, other.id);
	}
}