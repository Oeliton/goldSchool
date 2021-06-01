package com.goldschool.domain.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goldschool.domain.exception.EntidadeEmUsoException;
import com.goldschool.domain.exception.EntidadeNaoEncontradaException;
import com.goldschool.domain.model.Student;
import com.goldschool.domain.repository.StudentRepository;

@Service
public class CadastroStudentService {
	
	@Autowired
	private StudentRepository studentRepository;

	
	public List<Student> findList(){
		return studentRepository.findAll();
	}
	
	public Optional<Student> findById(Long id){
		
		if(id == null && id <= 0) return null;
		
		return Optional.ofNullable(studentRepository.findId(id));
	}
	
	public Student save(Student student) {
		try {
			if(student == null || student.getNome() == null || student.getNome().trim().length() <= 0) {
				throw new EntidadeNaoEncontradaException("Falta imformações para preencher");
			}
			return studentRepository.save(student);
		}catch (EmptyResultDataAccessException e){
	        throw new EntidadeNaoEncontradaException("Não foi possível cadastra");
	    }catch (DataIntegrityViolationException e){
	        throw new EntidadeEmUsoException("Estudante não pode ser cadastrado");
	    }
		
	}
	
	public void remove(Long id) {
		try {
			
			studentRepository.deleteById(id);
			
		}catch (EmptyResultDataAccessException e){
	        throw new EntidadeNaoEncontradaException(String.format("Não existe estudante %d", id));
	    }catch (DataIntegrityViolationException e){
	        throw new EntidadeEmUsoException(String.format("Estudante %d Não pode ser removida esta em uso", id));
	    }
	}
	
	public Student partialUpdate(Long id, Map<String, Object> dadosAtualizar){
        try{

            Optional<Student> student = studentRepository.findById(id);

            if (!student.isPresent()) {
                throw new EmptyResultDataAccessException(1);
            }

            merge(dadosAtualizar, student.get());

            return studentRepository.save(student.get());
            
        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException("Não existe este estudante");
        }
    }

    private void merge(Map<String, Object> dadosAtualizar, Student student) {
        ObjectMapper objectMapper = new ObjectMapper();
        Student studentOrigem = objectMapper.convertValue( dadosAtualizar, Student.class);

        dadosAtualizar.forEach((nomePropriedade, valorPropriedade) -> {

            //Pega qual a possição do campo
            Field field = ReflectionUtils.findField(Student.class, nomePropriedade);
            //Libera o acesso a coluna private
            field.setAccessible(true);
            Object novoValor = ReflectionUtils.getField( field, studentOrigem);
            ReflectionUtils.setField( field, student, novoValor);

        });
    }
}
