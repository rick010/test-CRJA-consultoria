package com.ricardosoftcom.testparadev.servicos;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ricardosoftcom.testparadev.dto.PessoaDTO;
import com.ricardosoftcom.testparadev.entidades.Departamento;
import com.ricardosoftcom.testparadev.entidades.Pessoa;
import com.ricardosoftcom.testparadev.entidades.Tarefa;
import com.ricardosoftcom.testparadev.repositorios.DepartamentoRepositorio;
import com.ricardosoftcom.testparadev.repositorios.PessoaRepositorio;
import com.ricardosoftcom.testparadev.repositorios.TarefaRepositorio;
import com.ricardosoftcom.testparadev.servicos.exceptions.DatabaseException;
import com.ricardosoftcom.testparadev.servicos.exceptions.ResourceNotFoundException;

@Service
public class PessoaServico {
	
	@Autowired
	PessoaRepositorio repositorio;
	
	@Autowired
	TarefaRepositorio tarefaRepositorio;
	
	@Autowired
	DepartamentoRepositorio departamentoRepositorio;
	
	@Transactional(readOnly = true)
	public Page<PessoaDTO> findAllPaged(Pageable pageable) {
		Page<Pessoa> list = repositorio.findAll(pageable);
//		return list.map(x -> new PessoaDTO(x, totalHorasGastasPorTarefas(x.getId())));
		return list.map(x -> new PessoaDTO(x));
	}
	
	@Transactional(readOnly = true)
	public List<PessoaDTO> findByAverageHoursSpentByTasks() {
		List<Pessoa> list = repositorio.findAll();
		List<PessoaDTO> listDTO = new ArrayList<>();
		for(Pessoa p : list) {
			Long media = totalHorasGastasPorTarefas(p.getId())/listTodasTarefasDaPessoa(p.getId()).size();
			listDTO.add(new PessoaDTO(p, totalHorasGastasPorTarefas(p.getId()), media));
		}
		return listDTO;
	}
	
	@Transactional
	public PessoaDTO insert(PessoaDTO dto) {
		Pessoa entity = new Pessoa();
		copyyDtoToEntity(dto, entity);
		entity = repositorio.save(entity);
		return new PessoaDTO(entity);
	}
	
	@Transactional
	public PessoaDTO update(Long id, PessoaDTO dto) {
		try {
			Pessoa entity = repositorio.getReferenceById(id);
			copyyDtoToEntity(dto, entity);
			entity = repositorio.save(entity);
			return new PessoaDTO(entity);
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}
	
	public void delete(Long id) {
		try {
			repositorio.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity vilation");
		}
	}
	
	public List<Tarefa> listTodasTarefasDaPessoa(Long id) {
		List<Tarefa> listTarefasPessoa = new ArrayList<>();
		try {
			List<Tarefa> listTarefas = tarefaRepositorio.findAll();
			
			for(Tarefa t : listTarefas) {
				
				if(id == t.getPessoa().getId()) {
				listTarefasPessoa.add(t);
				}
			}
		}catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
		return listTarefasPessoa;
	}
	
	public Long totalHorasGastasPorTarefas(Long id) {
		
		LocalDateTime dataAtual = LocalDateTime.now();
		List<Long> listHorasTarefasAlocadas = new ArrayList<>();
		Long totalHorasGastasNasTarefas = 0L;
		try {
			List<Tarefa> listTarefas = tarefaRepositorio.findAll();
			
			for(Tarefa t : listTarefas) {
				LocalDateTime dataInicio = t.getPrazo().plusDays(- t.getDuracao());
				if(!t.isFinalizado() && id == t.getPessoa().getId()) {
					listHorasTarefasAlocadas.add(Duration.between(dataInicio, dataAtual).toHours());
				}
			}
			for(Long l : listHorasTarefasAlocadas) {
				totalHorasGastasNasTarefas += l;
			}
			 
		}catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
		return totalHorasGastasNasTarefas;
	}
	
	private void copyyDtoToEntity(PessoaDTO dto, Pessoa entity) {
		
		entity.setNome(dto.getNome());
		Departamento departamento = departamentoRepositorio.getReferenceById(dto.getIdDepartamento());
		entity.setPessoaDepartamento(departamento);
		}
}