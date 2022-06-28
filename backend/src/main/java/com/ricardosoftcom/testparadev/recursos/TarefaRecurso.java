package com.ricardosoftcom.testparadev.recursos;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ricardosoftcom.testparadev.dto.TarefaDTO;
import com.ricardosoftcom.testparadev.servicos.TarefaServico;

@RestController
@RequestMapping(value = "/tarefas")
public class TarefaRecurso {
	
	@Autowired
	TarefaServico servico;
	
	@GetMapping
	public ResponseEntity<Page<TarefaDTO>> findAll(Pageable pageable) {
		
		Page<TarefaDTO> list = servico.findAllPaged(pageable);
		
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<TarefaDTO> insert(@RequestBody TarefaDTO dto) {
		dto = servico.alocarPessoa(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/alocar/{id}")
	public ResponseEntity<TarefaDTO> insertPessoaNaTarefa(@PathVariable Long id, @RequestBody TarefaDTO dto) {
		dto = servico.insertPessoaNaTarefa(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@PutMapping(value = "/finalizar/{id}")
	public ResponseEntity<TarefaDTO> finalizarTarefa(@PathVariable Long id, @RequestBody TarefaDTO dto) {
		dto = servico.finalizarTarefa(id, dto);
		return ResponseEntity.ok().body(dto);
	}

}
