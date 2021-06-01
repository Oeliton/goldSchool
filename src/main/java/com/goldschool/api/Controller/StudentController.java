package com.goldschool.api.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import com.goldschool.domain.exception.EntidadeEmUsoException;
import com.goldschool.domain.exception.EntidadeNaoEncontradaException;
import com.goldschool.domain.model.Student;
import com.goldschool.domain.service.CadastroStudentService;

@CrossOrigin(origins  = "http://localhost:4200", maxAge = 3600)
//@RequestMapping(value = "/student", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}) //Abilita que a resposta pode ser em Json ou XML
@Controller
@ResponseBody
@RequestMapping(value = "/student")
public class StudentController {
	
	@Autowired
	private CadastroStudentService cadastroStudentService;

	//Teste de conexão
	@RequestMapping(value="/teste", method = RequestMethod.GET)
	public String testeConexao() {
		return "Sucesso";
	}
	
	//Retorna lista de Student
	@GetMapping("/list")
	public ResponseEntity<?> findByList() {
		
		List<Student> teste = cadastroStudentService.findList();
		
		return ResponseEntity.ok(teste);
		
		//return ResponseEntity.ok(cadastroStudentService.findList());
	}
	
	
	//Retorna pesquisa feita por id no Student
	@GetMapping("/id/{id}")
    public ResponseEntity<Student> findById(@PathVariable Long id){
		Optional<Student> student = cadastroStudentService.findById(id);
		
		if(student != null && student.isPresent()){
            return ResponseEntity.ok(student.get());
        }
		
		return ResponseEntity.notFound().build(); 
    }
	
	//Salva Student
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> save(@RequestBody Student student) {
		try {
			
			student = cadastroStudentService.save(student);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(student);
        }catch (EntidadeNaoEncontradaException e){
            //return ResponseEntity.notFound().build();
        	return ResponseEntity.of(Optional.ofNullable("Falta imformações para preencher"));
        }
	}
	
	@PutMapping("/update/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student student){
        Optional<Student> studentAtual = cadastroStudentService.findById(id);

        if(!studentAtual.isPresent()){
            return ResponseEntity.notFound().build();
        }
        
        BeanUtils.copyProperties(student, studentAtual.get(), "id");

        Student studentSave = cadastroStudentService.save(studentAtual.get());
        
        return ResponseEntity.ok(studentSave);
        
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Student> delete(@PathVariable Long id){
        try {

        	cadastroStudentService.remove(id);
        	
            return ResponseEntity.noContent().build();

        }catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }
    }
    
    @PatchMapping("/partial/{id}")
    public ResponseEntity<?> partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> dadosAtualizar){
        return ResponseEntity.ok(cadastroStudentService.partialUpdate( id, dadosAtualizar));
    }
    
}
