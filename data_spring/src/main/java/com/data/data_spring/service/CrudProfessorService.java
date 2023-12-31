package com.data.data_spring.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.data.data_spring.entities.Disciplina;
import com.data.data_spring.entities.Professor;
import com.data.data_spring.repository.ProfessorRepository;

import jakarta.transaction.Transactional;

@Service
public class CrudProfessorService {

    private ProfessorRepository professorRepository;
    // Ponto de injeção de dependência da class ProfessorRepository
    public CrudProfessorService(ProfessorRepository professorRepository){
        this.professorRepository = professorRepository;
    }

    @Transactional
    public void menu(Scanner scanner){
        Boolean isTrue = true;

        while (isTrue){
            System.out.println("Qual ação você quer executar?");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.println("1 - Cadastrar novo Professor");
            System.out.println("2 - Atualizar um Professor");
            System.out.println("3 - Visualizar todos os Professor");
            System.out.println("4 - Deletar um Professor");
            System.out.println("5 - Visualizar um Professor");

            int opcao = scanner.nextInt();

            switch (opcao){
                case 1:
                    this.cadastrar(scanner);
                    break;
                case 2:
                    this.atualizar(scanner);
                    break;
                case 3:
                    this.visualizar();
                    break;
                case 4:
                    this.deletar(scanner);
                    break; 
                case 5:
                    this.visualizarProfessor(scanner);
                    break;   
                default:
                    isTrue = false;
                    break;
            }
        }
        System.out.println();
    }

    private void cadastrar(Scanner scanner){
		System.out.print("Digite o nome do professor: ");
		String nome = scanner.next();

		System.out.print("Digite o prontuario do professor: ");
		String prontuario = scanner.next();

		Professor professor = new Professor(nome, prontuario);
        this.professorRepository.save(professor);
        System.out.println("Professor salvo com sucesso!!!\n");
	}
    
    private void atualizar(Scanner scanner){
        System.out.println("Digite o Id do Professor a ser atualziado: ");
        Long id = scanner.nextLong();

        // Optional checagem se a informação existe
        Optional<Professor> idOptionalProfessor = this.professorRepository.findById(id);

        //Retorna o ID se for existente 
        if (idOptionalProfessor.isPresent()){ 
            System.out.print("Digite o nome do professor: ");
            String nome = scanner.next();

            System.out.print("Digite o prontuario do professor: ");
            String prontuario = scanner.next();

            Professor professor = idOptionalProfessor.get();
            professor.setNome(nome);
            professor.setProntuario(prontuario);
            this.professorRepository.save(professor); //Salva o registro no Banco de Dados

            System.out.println("Professor atualizado com sucesso!!!\n");
        }
        else {
            System.out.println("O Id informado: " + id + " é inválido\n");
        }
    }

    private void visualizar(){
        Iterable<Professor> professores = this.professorRepository.findAll();
        for(Professor professor : professores){
            System.out.println(professor);
        }
        System.out.println();
    }

    private void deletar(Scanner scanner){
        System.out.println("Digite o Id do Professor a ser deletado: ");
        Long id = scanner.nextLong();
        this.professorRepository.deleteById(id);
        System.out.println("Professor Deletado!!!\n");
    }

    @Transactional
    private void visualizarProfessor(Scanner scanner){
        System.out.println("Id do Professor: ");
        Long id =scanner.nextLong();

        Optional<Professor> idOptionalProfessor = professorRepository.findById(id);
        if (idOptionalProfessor.isPresent()){
            Professor professor = idOptionalProfessor.get();

            System.out.println("ID: " +  professor.getId());
            System.out.println("Nome: " +professor.getNome());
            System.out.println("Prontuario: " + professor.getProntuario());
            System.out.println("Discplina: [");

            if(professor.getDisciplinas() != null){
            for (Disciplina disciplina : professor.getDisciplinas()){
                System.out.println("\tId: " + disciplina.getId());
                System.err.println("\tNome: " + disciplina.getNome());
                System.out.println("\tSemestre: " + disciplina.getSemestre());
            }
        }  
            System.out.println("]\n}");
        }
    }
}
