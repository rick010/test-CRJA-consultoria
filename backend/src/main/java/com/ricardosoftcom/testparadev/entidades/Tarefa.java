package com.ricardosoftcom.testparadev.entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_tarefa")
public class Tarefa implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	
	@Column(columnDefinition = "TEXT")
	private String descricao;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private LocalDateTime prazo;
	private Integer duracao;
	private boolean finalizado;
	
	@ManyToOne
	@JoinColumn(name = "id_departamento")
	private Departamento departamento;
	
	@ManyToOne
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;
	
	public Tarefa() {
	}

	public Tarefa(Long id, String titulo, String descricao, Integer duracao, boolean finalizado,
			Departamento departamento, Pessoa pessoa) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.duracao = duracao;
		this.finalizado = finalizado;
		this.departamento = departamento;
		this.pessoa = pessoa;
		LocalDateTime dataAtual = LocalDateTime.now();
		LocalDateTime diaPrazo = dataAtual.plusDays(duracao);
		this.prazo = diaPrazo;
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

	public Integer getDuracao() {
		return duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
		LocalDateTime dataAtual = LocalDateTime.now();
		LocalDateTime diaPrazo = dataAtual.plusDays(duracao);
		this.prazo = diaPrazo;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarefa other = (Tarefa) obj;
		return Objects.equals(id, other.id);
	}
}