package com.ricardosoftcom.testparadev.servicos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ricardosoftcom.testparadev.dto.DepartamentoDTO;
import com.ricardosoftcom.testparadev.entidades.Departamento;
import com.ricardosoftcom.testparadev.entidades.Pessoa;
import com.ricardosoftcom.testparadev.entidades.Tarefa;
import com.ricardosoftcom.testparadev.repositorios.DepartamentoRepositorio;
import com.ricardosoftcom.testparadev.repositorios.PessoaRepositorio;
import com.ricardosoftcom.testparadev.repositorios.TarefaRepositorio;

@Service
public class DepartamentoServico {
	
	@Autowired
	DepartamentoRepositorio repositorio;
	
	@Autowired
	TarefaRepositorio tarefaRepositorio;
	
	@Autowired
	PessoaRepositorio pessoaRepositorio;
	
	@Transactional(readOnly = true)
	public List<DepartamentoDTO> todasDepartamentosQuantPessoasTarefas() {
		List<Departamento> listDepartamento = repositorio.findAll();
		List<Pessoa> listPessoa = pessoaRepositorio.findAll();
		List<Tarefa> listTarefa = tarefaRepositorio.findAll();
		List<DepartamentoDTO>  listDTO = new ArrayList<>();
		for(Departamento d : listDepartamento) {
			DepartamentoDTO dto = new DepartamentoDTO(d);
			for(Pessoa p : listPessoa) {
				if(p.getPessoaDepartamento().getId() == d.getId()) {
					dto.setQuantidadePessoas(dto.getQuantidadePessoas()+1);
				}
			}
			for(Tarefa t : listTarefa) {
				if(t.getDepartamento().getId() == d.getId()) {
					dto.setQuantidadeTarefas(dto.getQuantidadeTarefas()+1);
				}
			}
			dto.setQuantidadeTarefasFinalizadas(null);
			dto.setQuantidadeTarefasNãoFinalizadas(null);
			listDTO.add(dto);
		}
		return listDTO;
	}
	
	@Transactional(readOnly = true)
	public List<DepartamentoDTO> selectNomeDepartEQuantTarefasFinalizadasENaoFinalizadas(String titulo) {
		List<Departamento> listDepartamento = repositorio.findByTitulo(titulo);
		List<Tarefa> listTarefa = tarefaRepositorio.findAll();
		List<DepartamentoDTO> listDTO = new ArrayList<>();
		for(Departamento d : listDepartamento) {
			DepartamentoDTO dto = new DepartamentoDTO(d);
			for(Tarefa f : listTarefa) {
				if(f.isFinalizado() == true && f.getDepartamento().getId() == d.getId()) {
					dto.setQuantidadeTarefasFinalizadas(dto.getQuantidadeTarefasFinalizadas()+1);
				}else if (f.isFinalizado() == false && f.getDepartamento().getId() == d.getId()){
					dto.setQuantidadeTarefasNãoFinalizadas(dto.getQuantidadeTarefasNãoFinalizadas()+1);
				}
			}
			dto.setQuantidadePessoas(null);
			dto.setQuantidadeTarefas(null);
			listDTO.add(dto);
		}
		return listDTO;
	}
}
