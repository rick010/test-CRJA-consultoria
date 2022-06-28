package com.ricardosoftcom.testparadev.servicos;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ricardosoftcom.testparadev.dto.TarefaDTO;
import com.ricardosoftcom.testparadev.entidades.Departamento;
import com.ricardosoftcom.testparadev.entidades.Pessoa;
import com.ricardosoftcom.testparadev.entidades.Tarefa;
import com.ricardosoftcom.testparadev.repositorios.DepartamentoRepositorio;
import com.ricardosoftcom.testparadev.repositorios.PessoaRepositorio;
import com.ricardosoftcom.testparadev.repositorios.TarefaRepositorio;
import com.ricardosoftcom.testparadev.servicos.exceptions.ResourceNotFoundException;

@Service
public class TarefaServico {
	
	@Autowired
	TarefaRepositorio repositorio;
	
	@Autowired
	PessoaRepositorio pessoaRepositorio;
	
	@Autowired
	DepartamentoRepositorio departamentoRepositorio;
	
	@Transactional(readOnly = true)
	public Page<TarefaDTO> findAllPaged(Pageable pageable) {
		Page<Tarefa> list = repositorio.findAll(pageable);
		return list.map(x -> new TarefaDTO(x));
	}
	
	@Transactional
	public TarefaDTO alocarPessoa(TarefaDTO dto) {
		Tarefa entity = new Tarefa();
		copyyDtoToEntity(dto, entity);
		entity = repositorio.save(entity);
		return new TarefaDTO(entity);
	}
	
	@Transactional
	public TarefaDTO insertPessoaNaTarefa(Long id, TarefaDTO dto) {
		try {
			Tarefa entity = repositorio.getReferenceById(id);
			if(entity.getDepartamento().getId() == (dto.getIdDepartamento())) {
				copyyDtoToEntityInsertPessoa(dto, entity);
				entity = repositorio.save(entity);
			}
			return new TarefaDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}
	
	@Transactional
	public TarefaDTO finalizarTarefa(Long id, TarefaDTO dto) {
		try {
			Tarefa entity = repositorio.getReferenceById(id);
			copyyDtoToEntityfinalizarTarefa(dto, entity);
				entity = repositorio.save(entity);
			return new TarefaDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}
	private void copyyDtoToEntityfinalizarTarefa(TarefaDTO dto, Tarefa entity) {
		
		entity.setTitulo(dto.getTitulo());
		entity.setDescricao(dto.getDescricao());
		entity.setDuracao(dto.getDuracao());
		entity.setFinalizado(true);
		Departamento departamento = departamentoRepositorio.getReferenceById(dto.getIdDepartamento());
		entity.setDepartamento(departamento);
		entity.setPessoa(null);
	}
		
	private void copyyDtoToEntityInsertPessoa(TarefaDTO dto, Tarefa entity) {
		
		entity.setTitulo(dto.getTitulo());
		entity.setDescricao(dto.getDescricao());
		entity.setDuracao(dto.getDuracao());
		entity.setFinalizado(dto.isFinalizado());
		Departamento departamento = departamentoRepositorio.getReferenceById(dto.getIdDepartamento());
		entity.setDepartamento(departamento);
		Pessoa pessoa = pessoaRepositorio.getReferenceById(dto.getIdPessoa());
		entity.setPessoa(pessoa);
	}	
	private void copyyDtoToEntity(TarefaDTO dto, Tarefa entity) {
		
		entity.setTitulo(dto.getTitulo());
		entity.setDescricao(dto.getDescricao());
		entity.setDuracao(dto.getDuracao());
		entity.setFinalizado(dto.isFinalizado());
		Departamento departamento = departamentoRepositorio.getReferenceById(dto.getIdDepartamento());
		entity.setDepartamento(departamento);
	}



}
