package com.data.data_spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.data.data_spring.entities.Aluno;
import com.data.data_spring.entities.Disciplina;
import com.data.data_spring.entities.Professor;
import com.data.data_spring.repository.AlunoRepository;
import com.data.data_spring.repository.DisciplinaRepository;
import com.data.data_spring.repository.ProfessorRepository;

import jakarta.transaction.Transactional;

@Service
public class CrudDisciplinaService {
    private DisciplinaRepository disciplinaRepository;
    private ProfessorRepository professorRepository;
    private AlunoRepository alunoRepository;

    public CrudDisciplinaService(DisciplinaRepository disciplinaRepository, ProfessorRepository professorRepository,
            AlunoRepository alunoRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.professorRepository = professorRepository;
        this.alunoRepository = alunoRepository;
    }

    @Transactional
    public void menu(Scanner scanner) {
        Boolean isTrue = true;

        while (isTrue) {
            System.out.println("\nQual opção você quer realizar?");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.println("1 - Cadastrar nova Disciplina");
            System.out.println("2 - Atualizar uma Discplina");
            System.out.println("3 - Visualizar todas Disciplinas");
            System.out.println("4 - Deletar uma Discplina");
            System.out.println("5 - Matricular Alunos");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    cadastrar(scanner);
                    break;
                case 2:
                    atualizar(scanner);
                    break;
                case 3:
                    visualizar(scanner);
                    break;
                case 4:
                    delatar(scanner);
                    break;
                case 5:
                    matricularAlunos(scanner);
                    break;
                default:
                    isTrue = false;
                    break;
            }
        }
        System.out.println();
    }

    private void cadastrar(Scanner scanner) {
        System.out.println("Nome da disciplina: ");
        String nome = scanner.next();

        System.out.println("Semestre");
        Integer semestre = scanner.nextInt();

        System.out.println("Professor ID");
        Long professorId = scanner.nextLong();

        Optional<Professor> idOptionalProfessor = this.professorRepository.findById(professorId);

        if (idOptionalProfessor.isPresent()) {
            Professor professor = idOptionalProfessor.get();

            List<Aluno> alunos = this.matricular(scanner);

            Disciplina disciplina = new Disciplina(nome, semestre, professor);
            disciplina.setAlunos(alunos);
            disciplinaRepository.save(disciplina);
            System.out.println("Salvo com Sucesso!!!");
        } else {
            System.out.println("Professor ID " + professorId + " inválido");
        }
    }

    private void atualizar(Scanner scanner) {
        System.out.println("Digite o Id da disciplina a ser autualizada: ");
        Long id = scanner.nextLong();

        Optional<Disciplina> idOptionalDisciplina = this.disciplinaRepository.findById(id);
        if (idOptionalDisciplina.isPresent()) {
            Disciplina disciplina = idOptionalDisciplina.get();

            System.out.println("Nome da disciplina: ");
            String nome = scanner.next();

            System.out.println("Semestre: ");
            Integer semestre = scanner.nextInt();

            System.out.println("Professor ID: ");
            Long professorId = scanner.nextLong();

            Optional<Professor> idOptionalProfessor = this.professorRepository.findById(professorId);
            if (idOptionalProfessor.isPresent()) {
                Professor professor = idOptionalProfessor.get();

                List<Aluno> alunos = this.matricular(scanner);

                disciplina.setNome(nome);
                disciplina.setSemestre(semestre);
                disciplina.setProfessor(professor);
                disciplina.setAlunos(alunos);

                disciplinaRepository.save(disciplina);
                System.out.println("Atualizado com Sucesso!!!");
            } else {
                System.out.println("Professor ID " + professorId + " inválido");
            }
        } else {
            System.out.println("O id da disciplina informado: " + id + " é inválido\n");
        }
    }

    @Transactional
    private void visualizar(Scanner scanner) {
        Iterable<Disciplina> disciplinas = this.disciplinaRepository.findAll();
        for (Disciplina disciplina : disciplinas) {
            System.out.println(disciplina);
        }
    }

    private void delatar(Scanner scanner) {
        System.out.println("Id:");
        long id = scanner.nextLong();
        this.disciplinaRepository.deleteById(id); // Exception se não encontrar o ID
        System.out.println("Disciplina Deletada\n");

    }

    private List<Aluno> matricular(Scanner scanner) {
        Boolean isTrue = true;
        List<Aluno> alunos = new ArrayList<Aluno>();

        while (isTrue) {
            System.out.println("Id do aluno a ser matriculado (Digite 0 para sair)");
            Long alunoId = scanner.nextLong();

            if (alunoId > 0) {
                System.out.println("alunoId: " + alunoId);
                Optional<Aluno> optionalAluno = this.alunoRepository.findById(alunoId);
                if (optionalAluno.isPresent()) {
                    alunos.add(optionalAluno.get());
                } else {
                    System.out.println("Nenhum aluno possui id " + alunoId + "!");
                }
            } else {
                isTrue = false;
            }
        }

        return alunos;
    }

    private void matricularAlunos(Scanner scanner) {
        System.out.println("Qual o id da disciplina para matricular alunos: ");
        Long id = scanner.nextLong();

        Optional<Disciplina> optionalDisciplina = this.disciplinaRepository.findById(id);

        if (optionalDisciplina.isPresent()) {
            Disciplina disciplina = optionalDisciplina.get();
            List<Aluno> novosAlunos = this.matricular(scanner);
            disciplina.getAlunos().addAll(novosAlunos);
            this.disciplinaRepository.save(disciplina);

        } else {
            System.out.println("O id da disciplina informado " + id + "é inválido\n");
        }
    }
}
