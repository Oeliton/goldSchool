package com.goldschool.domain.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.Column;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.goldschool.domain.model.Student;

//@EnableJpaRepositories
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
	@Query(value = "select id, nome, cpf, sexo, estado_civil, email, fone, celular, dtnascimento from estudante where id = :ra_id", nativeQuery = true)
	Student findId(@Param("ra_id") Long Id);
	
	/*@Query(value = "select id, name from Estudante where estado_civil = :ra_estado_civil", nativeQuery = true)
    List<Student> buscaPorEstadoCivil(@Param("ra_estado_civil") String estadoCivil);

    @Modifying //permissão para alterar os dados
    @Query("update Estudante set name = :ra_nome where id = :id")
    void updateNome(@Param("id") Long id, @Param("ra_nome") String nome);*/
    
    //List<Student> buscaPorSexoEstadoCivil(@Param("ra_sexo") String sexo, @Param("ra_estadoCivil") String estadoCivil);
}
