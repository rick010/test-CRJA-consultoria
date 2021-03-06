package com.ricardosoftcom.testparadev.servicos;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
	public List<TarefaDTO> todasTarefas() {
		List<Tarefa> list = repositorio.findAll(Sort.by(Direction.ASC,"prazo"));
		List<TarefaDTO> listTarefa = new ArrayList<>();
		for(Tarefa t : list) {
			TarefaDTO dto = new TarefaDTO(t);
			if(t.getPessoa() != null) {
				dto.setMensagem("Encaminhado para "+ t.getPessoa().getNome());
				listTarefa.add(dto);
			}else if (t.getPessoa() == null){
				dto.setMensagem("Pendente " + " Totol de horas gastas: " +totalHorasGastasPorTodasTarefas(t.getId()));
				listTarefa.add(dto);
			}
		}
		return listTarefa;
	}
	
	@Transactional(readOnly = true)
	public List<TarefaDTO> tarefasSemPessoasAlocadas() {
		List<Tarefa> list = repositorio.findAll(Sort.by("prazo"));
		List<TarefaDTO> listTarefa = new ArrayList<>();
		for(Tarefa t : list) {
			if(t.getPessoa() == null) {
				listTarefa.add(new TarefaDTO(t));
			}
		}
		return listTarefa;
	}
	
	@Transactional
	public TarefaDTO adicionarTarefa(TarefaDTO dto) {
		Tarefa entity = new Tarefa();
		copyyDtoToEntity(dto, entity);
		entity = repositorio.save(entity);
		return new TarefaDTO(entity);
	}
	
	@Transactional
	public TarefaDTO alocarPessoaNaTarefa(Long id, TarefaDTO dto) {
		try {
			Tarefa entity = repositorio.getReferenceById(id);
			if(entity.getDepartamento().getId() == (dto.getIdDepartamento())) {
				copyyDtoToEntityAlocarPessoa(dto, entity);
				entity = repositorio.save(entity);
			}else {
				throw new ResourceNotFoundException("Deparmento tem que n??o pode ser diferente");
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
		
		LocalDateTime dataAtual = LocalDateTime.now();
		LocalDateTime dataInicio = entity.getPrazo().plusDays(- entity.getDuracao());
		int duracao = dataAtual.getDayOfMonth() - dataInicio.getDayOfMonth();
		LocalDateTime prazoAtualizado = dataInicio.plusDays(duracao);
		Duration.between(dataInicio, dataAtual);
		entity.setTitulo(dto.getTitulo());
		entity.setDescricao(dto.getDescricao());
		entity.setPrazo(prazoAtualizado);
		entity.setDuracao(duracao);
		entity.setFinalizado(true);
		Departamento departamento = departamentoRepositorio.getReferenceById(dto.getIdDepartamento());
		entity.setDepartamento(departamento);
		entity.setPessoa(null);
	}
		
	private void copyyDtoToEntityAlocarPessoa(TarefaDTO dto, Tarefa entity) {
		
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
	
	public Long totalHorasGastasPorTodasTarefas(Long id) {
		
		LocalDateTime dataAtual = LocalDateTime.now();
		List<Long> listHorasTarefasAlocadas = new ArrayList<>();
		Long totalHorasGastasNasTarefas = 0L;
		try {
			List<Tarefa> listTarefas = repositorio.findAll();
			
			for(Tarefa t : listTarefas) {
				LocalDateTime dataInicio = t.getPrazo().plusDays(- t.getDuracao());
				if(t.getPessoa() != null) {
					if(id == t.getPessoa().getId()) {
						listHorasTarefasAlocadas.add(Duration.between(dataInicio, dataAtual).toHours());
					}
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



}
