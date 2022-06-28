package com.ricardosoftcom.testparadev.recursos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ricardosoftcom.testparadev.servicos.DepartamentoServico;

@RestController
@RequestMapping(value = "/departamentos")
public class DepartamentoRecurso {

	@Autowired
	private DepartamentoServico servico;


	
}
