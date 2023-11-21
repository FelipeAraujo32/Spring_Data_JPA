package com.data.data_spring.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.data.data_spring.entities.Professor;
import com.data.data_spring.repository.ProfessorRepository;

@Service
public class CrudProfessorService {

    private ProfessorRepository professorRepository;
    // Ponto de injeção de dependência da class ProfessorRepository
    public CrudProfessorService(ProfessorRepository professorRepository){
        this.professorRepository = professorRepository;
    }

    public void menu(Scanner scanner){
        Boolean isTrue = true;

        while (isTrue){
            System.out.println("Qual ação você quer executar?");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.println("1 - Cadastrar novo Professor");
            System.out.println("2 - Atualizar um Professor");
            System.out.println("3 - Visualizartodos os Professor");
            System.out.println("4 - Deletar um Professor");

            int opcao = scanner.nextInt();

            switch (opcao){
                case 1:
                    this.cadastrar(scanner);
                    break;
                case 2:
                    this.autualizar(scanner);
                    break;
                case 3:
                    this.visualizar();
                    break;
                case 4:
                    this.deletar(scanner);
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
    
    private void autualizar(Scanner scanner){
        System.out.println("Digite o Id do Professor a ser atualziado: ");
        Long id = scanner.nextLong();

        // Optional checagem se a informação existe
        Optional<Professor> idOptional = this.professorRepository.findById(id);

        //Retorna o ID se for existente 
        if (idOptional.isPresent()){ 
            System.out.print("Digite o nome do professor: ");
            String nome = scanner.next();

            System.out.print("Digite o prontuario do professor: ");
            String prontuario = scanner.next();

            Professor professor = idOptional.get();
            professor.setNome(nome);
            professor.setProntuario(prontuario);
            professorRepository.save(professor); //Salva o registro no Banco de Dados

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
}
