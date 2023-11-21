package com.data.data_spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.data.data_spring.entities.Professor;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, Long>{
    
}
