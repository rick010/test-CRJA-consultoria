package com.ricardosoftcom.testparadev.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ricardosoftcom.testparadev.entidades.Pessoa;

@Repository
public interface PessoaRepositorio extends JpaRepository<Pessoa, Long>{

}
