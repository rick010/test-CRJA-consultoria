package com.ricardosoftcom.testparadev.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ricardosoftcom.testparadev.entidades.Tarefa;

@Repository
public interface TarefaRepositorio extends JpaRepository<Tarefa, Long>{

}
