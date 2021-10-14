package com.example.springboot.dto;

import java.util.List;

import com.example.springboot.model.Parent;
import com.example.springboot.repository.ParentRepository;

public class CRUDOperationsDTO {
	
	public List<Parent> displayAll(ParentRepository repository) {
		return repository.findAll();
	}

}
