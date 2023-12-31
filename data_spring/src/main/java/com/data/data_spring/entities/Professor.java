package com.data.data_spring.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;

@Entity
@Table(name="professores")
public class Professor {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String prontuario;

    @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY)
    private List<Disciplina> disciplinas;

    @Deprecated // Utilizado por bibliotecas externas
    public Professor(){ }

    public Professor(String nome, String prontuario){
        this.nome = nome;
        this.prontuario = prontuario;
    }

    public Long getId(){
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProntuario() {
        return prontuario;
    }

    public void setProntuario(String prontuario) {
        this.prontuario = prontuario;
    }

    public List<Disciplina> getDisciplinas(){
        return disciplinas;
    }

    public void setDisciplina(List<Disciplina> disciplinas){
        this.disciplinas = disciplinas;
    }

    @PreRemove // ON REMOVE SET NULL
    public void attDisciplina(){
        System.out.println("Atualizando professor da disciplina");
        for (Disciplina disciplina: this.getDisciplinas()){
            disciplina.setProfessor(null);
        }
    }

    @Override
    public String toString() {
        return "Professor{" +
        "id=" + id + 
        ", nome=" + nome + '\'' +
        ", prontuario=" + prontuario + '\'' +
        '}';
    }
    
    
    
}
