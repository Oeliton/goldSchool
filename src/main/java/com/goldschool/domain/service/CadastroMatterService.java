package com.goldschool.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldschool.domain.model.Matter;
import com.goldschool.domain.repository.MatterRepository;

@Service
public class CadastroMatterService {

	@Autowired
	private MatterRepository matterRepository;
	
	public List<Matter> findList(){
		return matterRepository.findAll();
	}
}
