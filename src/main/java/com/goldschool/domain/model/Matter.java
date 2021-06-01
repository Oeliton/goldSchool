package com.goldschool.domain.model;

import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "materia")
public class Matter {
	

	//Gera o equals e hashCode apenas para o id
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "descricao", length = 100)
    private String descricao;
    
    //@ManyToOne
    //@JoinColumn(name = "cozinha_id", nullable = false)
    //private Cozinha cozinha;
}
