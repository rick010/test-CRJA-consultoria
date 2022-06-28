package com.ricardosoftcom.testparadev.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricardosoftcom.testparadev.repositorios.DepartamentoRepositorio;

@Service
public class DepartamentoServico {
	
	@Autowired
	DepartamentoRepositorio repositorio;

}
