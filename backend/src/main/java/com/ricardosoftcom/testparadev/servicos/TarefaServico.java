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
import com.ricardosoftcom.testparadev.repositorios.PessoaRepositorio;
import com.ricardosoftcom.testparadev.repositorios.TarefaRepositorio;
import com.ricardosoftcom.testparadev.servicos.exceptions.ResourceNotFoundException;

@Service
public class TarefaServico {
	
	@Autowired
	TarefaRepositorio repositorio;
	
	@Autowired
	PessoaRepositorio pessoaRepositorio;
	
	@Transactional(readOnly = true)
	public Page<TarefaDTO> findAllPaged(Pageable pageable) {
		Page<Tarefa> list = repositorio.findAll(pageable);
		return list.map(x -> new TarefaDTO(x));
	}
	
	@Transactional
	public TarefaDTO insertPessoa(TarefaDTO dto) {
		Tarefa entity = new Tarefa();
		copyyDtoToEntity(dto, entity);
		entity = repositorio.save(entity);
		return new TarefaDTO(entity);
	}
	
	@Transactional
	public TarefaDTO insertPessoaNaTarefa(Long id, TarefaDTO dto) {
		try {
			Tarefa entity = repositorio.getReferenceById(id);
			if(entity.getDepartamento().getId().equals(dto.getIdDepartamento())) {
				copyyDtoToEntityInsertPessoa(dto, entity);
				entity = repositorio.save(entity);
			}
			return new TarefaDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}
		
	private void copyyDtoToEntityInsertPessoa(TarefaDTO dto, Tarefa entity) {
		
		entity.setTitulo(dto.getTitulo());
		entity.setDescricao(dto.getDescricao());
		entity.setDuracao(dto.getDuracao());
		entity.setFinalizado(false);
		entity.setDepartamento(new Departamento(dto.getIdDepartamento(), null));
		entity.setPessoa(new Pessoa(dto.getIdPessoa(), null, null));
	}	
	private void copyyDtoToEntity(TarefaDTO dto, Tarefa entity) {
		
		entity.setTitulo(dto.getTitulo());
		entity.setDescricao(dto.getDescricao());
		entity.setDuracao(dto.getDuracao());
		entity.setFinalizado(false);
		entity.setDepartamento(new Departamento(dto.getIdDepartamento(), null));
	}



}
