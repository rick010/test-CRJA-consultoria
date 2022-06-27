package com.ricardosoftcom.testparadev.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ricardosoftcom.testparadev.entidades.Departamento;

@Repository
public interface DepartamentoRepositorio extends JpaRepository<Departamento, Long>{

}
