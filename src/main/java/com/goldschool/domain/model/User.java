package com.goldschool.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "usuario")
public class User {
	
	@Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;
    
    @Column(name="sexo")
    private String sexo;
    
    @Column(name="email")
    private String email;
    
    @Column(name="fone")
    private String fone;
    
    @Column(name="celular")
    private String celular;

}
