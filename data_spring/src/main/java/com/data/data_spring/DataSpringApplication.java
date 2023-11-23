package com.data.data_spring;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.data.data_spring.service.CrudProfessorService;

@SpringBootApplication
public class DataSpringApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(DataSpringApplication.class, args);
	}

	private CrudProfessorService crudProfessorService;

	// Ponto de injeção  para da class CrudProfessorService e anotação @Service
	public DataSpringApplication(CrudProfessorService crudProfessorService){
		this.crudProfessorService = crudProfessorService;
	}

	@Override
	public void run(String... args) throws Exception {
		Boolean isTrue = true;
		Scanner scanner = new Scanner(System.in);

		while(isTrue){
			System.out.println("\nQual entidade você deseja interagir?");
			System.out.println("0 - Sair");
			System.out.println("1 - Professor");

			int opcao = scanner.nextInt();
			
			switch(opcao){
				case 1:
					this.crudProfessorService.menu(scanner);
					break;
				default:
					isTrue = false;
					break;
			}
		}
	}

}
