package com.goldschool.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.goldschool.domain.model.User;
import com.goldschool.domain.repository.UserRepository;

@Service
public class CadastroUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findList(){
		return userRepository.findAll();
	}

}
