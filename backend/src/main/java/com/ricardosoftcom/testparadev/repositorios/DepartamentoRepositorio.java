package com.ricardosoftcom.testparadev.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ricardosoftcom.testparadev.entidades.Departamento;

@Repository
public interface DepartamentoRepositorio extends JpaRepository<Departamento, Long>{

	List<Departamento> findByTitulo(String titulo);
}
