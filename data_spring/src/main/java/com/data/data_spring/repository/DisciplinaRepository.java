package com.data.data_spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.data.data_spring.entities.Disciplina;

@Repository
public interface DisciplinaRepository extends CrudRepository<Disciplina, Long> {
    
}
