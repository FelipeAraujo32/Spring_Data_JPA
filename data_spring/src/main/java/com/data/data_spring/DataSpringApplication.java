package com.data.data_spring;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.data.data_spring.service.CrudAlunoService;
import com.data.data_spring.service.CrudDisciplinaService;
import com.data.data_spring.service.CrudProfessorService;

@SpringBootApplication
public class DataSpringApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(DataSpringApplication.class, args);
	}

	private CrudProfessorService crudProfessorService;
	private CrudDisciplinaService crudDisciplinaService;
	private CrudAlunoService crudAlunoService;

	// Ponto de injeção  para da class CrudProfessorService e anotação @Service
	public DataSpringApplication(CrudProfessorService crudProfessorService, CrudDisciplinaService crudDisciplinaService, CrudAlunoService crudAlunoService){
		this.crudProfessorService = crudProfessorService;
		this.crudDisciplinaService = crudDisciplinaService;
		this.crudAlunoService = crudAlunoService;
	}

	@Override
	public void run(String... args) throws Exception {
		Boolean isTrue = true;
		Scanner scanner = new Scanner(System.in);

		while(isTrue){
			System.out.println("Oque deseja fazer?");
			System.out.println("0 - Sair");
			System.out.println("1 - Professor");
			System.out.println("2 - Disciplina");
			System.out.println("3 - Alunos");
			System.out.println("4 - Relatório");

			int opcao = scanner.nextInt();
			
			switch(opcao){
				case 1:
					this.crudProfessorService.menu(scanner);
					break;
				case 2:
					this.crudDisciplinaService.menu(scanner);
					break;
				case 3:
					this.crudAlunoService.menu(scanner);
					break;
				default:
					isTrue = false;
					break;
			}
		}
	}

}
