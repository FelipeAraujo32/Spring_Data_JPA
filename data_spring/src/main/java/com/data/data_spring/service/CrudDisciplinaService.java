package com.data.data_spring.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.data.data_spring.entities.Disciplina;
import com.data.data_spring.entities.Professor;
import com.data.data_spring.repository.DisciplinaRepository;
import com.data.data_spring.repository.ProfessorRepository;

@Service
public class CrudDisciplinaService {
    private DisciplinaRepository disciplinaRepository;
    private ProfessorRepository professorRepository;

    public CrudDisciplinaService(DisciplinaRepository disciplinaRepository, ProfessorRepository professorRepository){
    this.disciplinaRepository = disciplinaRepository;
    this.professorRepository = professorRepository;
    }

    public void menu(Scanner scanner){
        Boolean isTrue = true;

        while (isTrue){
            System.out.println("\nQual opção você quer realizar?");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.println("1 - Cadastrar nova Disciplina");
            System.out.println("2 - Atualizar uma Discplina");
            System.out.println("3 - Visualizar toodas Disciplinas");
            System.out.println("4 - Deletar uma Discplina");

            int opcao = scanner.nextInt();

            switch (opcao){
                case 1:
                    cadastrar(scanner);
                    break;
                case 2:
                    atualizar(scanner);
                    break;
                case 3:
                    ///
                    break;
                case 4:
                    ///
                    break;
                default:
                    isTrue = false;
                    break;
            }
        }  
        System.out.println();
    }

    private void cadastrar(Scanner scanner){
        System.out.println("Nome da disciplina: ");
        String nome = scanner.next();

        System.out.println("Semestre");
        Integer semestre = scanner.nextInt();

        System.out.println("Professor ID");
        Long professorId = scanner.nextLong();

        Optional<Professor> idOptionalProfessor = this.professorRepository.findById(professorId);

        if(idOptionalProfessor.isPresent()){
            Professor professor = idOptionalProfessor.get();
            Disciplina disciplina = new Disciplina(nome, semestre, professor);
            disciplinaRepository.save(disciplina);
            System.out.println("Salvo com Sucesso!!!");
        }
        else{
            System.out.println("Professor ID " + professorId + "inválido");
        }
    }

    private void atualizar(Scanner scanner){
        System.out.println("Digite o Id da disciplina a ser autualizada: ");
        Long id = scanner.nextLong();

        Optional<Disciplina> idOptionalDisciplina = this.disciplinaRepository.findById(id);
        if (idOptionalDisciplina.isPresent()){
            Disciplina disciplina = idOptionalDisciplina.get();

            System.out.println("Nome da disciplina: ");
            String nome = scanner.next();

            System.out.println("Semestre: ");
            Integer semestre = scanner.nextInt();

            System.out.println("Professor ID: ");
            Long professorId = scanner.nextLong();

            Optional<Professor> idOptionalProfessor = this.professorRepository.findById(professorId);
            if(idOptionalProfessor.isPresent()){
                Professor professor = idOptionalProfessor.get();

                disciplina.setNome(nome);
                disciplina.setSemestre(semestre);
                disciplina.setProfessor(professor);

                disciplinaRepository.save(disciplina);
                System.out.println("Atualizado com Sucesso!!!");
            }
            else{
            System.out.println("Professor ID " + professorId + "inválido");
            }
        }
        else{
            System.out.println("O id da disciplina informado: " + id + "é inválido\n");
        }
    }
}
