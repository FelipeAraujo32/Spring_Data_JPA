package com.data.data_spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.data.data_spring.entities.Aluno;

@Repository
public interface AlunoRepository extends CrudRepository<Aluno, Long> {
    
}
