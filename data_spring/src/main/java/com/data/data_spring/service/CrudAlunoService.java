package com.data.data_spring.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.data.data_spring.entities.Aluno;
import com.data.data_spring.entities.Disciplina;
import com.data.data_spring.repository.AlunoRepository;

import jakarta.transaction.Transactional;

@Service
public class CrudAlunoService {

    private AlunoRepository alunoRepository;

    public CrudAlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Transactional
    public void menu(Scanner scanner) {
        Boolean isTrue = true;

        while (isTrue) {
            System.out.println("\nQual opção você quer realizar?");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.println("1 - Cadastrar novo Aluno");
            System.out.println("2 - Atualizar um Aluno");
            System.out.println("3 - Visualizar todos os Alunos");
            System.out.println("4 - Deletar uma Discplina\n");
            System.out.println("5 - Visualizar um Aluno");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    this.cadastrar(scanner);
                    break;
                case 2:
                    this.autualizar(scanner);
                    break;
                case 3:
                    this.visualizar(scanner);
                    break;
                case 4:
                    this.deletar(scanner);
                    break;
                case 5:
                    this.visualizarAluno(scanner);
                    break;

            }
        }
        System.out.println();
    }

    private void cadastrar(Scanner scanner) {
        System.out.println("Qual o nome do Aluno: ");
        String nome = scanner.next();

        System.out.println("Qual a idade do Aluno: ");
        Integer idade = scanner.nextInt();

        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setIdade(idade);
        this.alunoRepository.save(aluno);
        System.out.println("Aluno Salvo com Sucesso!!!");
    }

    private void autualizar(Scanner scanner){
        System.out.println("Qual o Id do Aluno:  ");
        Long id = scanner.nextLong();

        Optional<Aluno> idOptionalAluno = this.alunoRepository.findById(id);

        if(idOptionalAluno.isPresent()){
        System.out.println("Qual o nome do Aluno: ");
        String nome = scanner.next();

        System.out.println("Qual a idade do Aluno: ");
        Integer idade = scanner.nextInt();

        Aluno aluno = idOptionalAluno.get();
        aluno.setNome(nome);
        aluno.setIdade(idade);
        this.alunoRepository.save(aluno);
        System.out.println("Aluno atualizado com sucesso!!!\n");
        }
        else {
            System.out.println("O Id informado: " + id + " é inválido\n");
        }
    }
    
    private void visualizar(Scanner scanner){
        Iterable<Aluno> alunos = this.alunoRepository.findAll();
        for(Aluno aluno : alunos){
            System.out.println(aluno);
        }
        System.out.println();
    }

    private void deletar(Scanner scanner){
        System.out.println("Qual Id deseja Deletar: ");
        Long id = scanner.nextLong();
        this.alunoRepository.deleteById(id);
        System.out.println("Aluno deletado!!!");
    }
    
    @Transactional
    private void visualizarAluno(Scanner scanner){
        System.out.println("Digite o Id do aluno a ser visualizado: ");
        long id = scanner.nextLong();

        Optional<Aluno> idOptionalAluno = this.alunoRepository.findById(null);

        if(idOptionalAluno.isPresent()){
            Aluno aluno = idOptionalAluno.get();
            System.out.println(" - ID: " + aluno.getId());
            System.out.println("- Nome: " + aluno.getNome());
            System.out.println("- Idade: " + aluno.getIdade());
            System.out.println("- Disciplina: [:");

            if(aluno.getDisciplinas() != null){
                for (Disciplina disciplina : aluno.getDisciplinas()){
                System.out.println("\t- Disciplina: " + disciplina.getNome());
                System.out.println("\t- Semestre: " + disciplina.getSemestre());
                System.out.println();
                }
            }
            System.out.println("");
        }
        else{
            System.out.println("O Id informado: " + id + " é inválido\n");
        }
        
    }
}
