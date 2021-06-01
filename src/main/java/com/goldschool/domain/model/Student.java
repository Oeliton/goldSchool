package com.goldschool.domain.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Table(name="estudante")
public class Student {

	private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome")
    private String nome;
    
    @Column(name="cpf")
    private String cpf;
    
    @Column(name="sexo")
    private String sexo;
    
    @Column(name="estado_civil")
    private String estado_civil;
    
    @Column(name="email")
    private String email;
    
    @Column(name="fone")
    private String fone;
    
    @Column(name="celular")
    private String celular;
    
    @Column(name="dtnascimento")
    private String dtnascimento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEstado_civil() {
		return estado_civil;
	}

	public void setEstado_civil(String estado_civil) {
		this.estado_civil = estado_civil;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getDtnascimento() {
		return dtnascimento;
	}

	public void setDtnascimento(String dtnascimento) {
		this.dtnascimento = dtnascimento;
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
        
	
}
