package com.ricardosoftcom.testparadev.recursos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ricardosoftcom.testparadev.dto.DepartamentoDTO;
import com.ricardosoftcom.testparadev.servicos.DepartamentoServico;

@RestController
@RequestMapping(value = "/departamentos")
public class DepartamentoRecurso {

	@Autowired
	private DepartamentoServico servico;

	@GetMapping
	public ResponseEntity<List<DepartamentoDTO>> todasDepartamentosQuantPessoasTarefas() {
		List<DepartamentoDTO> list = servico.todasDepartamentosQuantPessoasTarefas();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/titulo")
	public ResponseEntity<List<DepartamentoDTO>> selectNomeDepartEQuantTarefasFinalizadasENaoFinalizadas(@RequestParam String titulo) {
		List<DepartamentoDTO> list = servico.selectNomeDepartEQuantTarefasFinalizadasENaoFinalizadas(titulo);
		return ResponseEntity.ok().body(list);
	}
}
