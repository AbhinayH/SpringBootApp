package com.example.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.dto.CRUDOperationsDTO;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.model.Parent;
import com.example.springboot.repository.ChildRepository;
import com.example.springboot.repository.ParentRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class MyController {

	@Autowired
	private ParentRepository repository;
	
	CRUDOperationsDTO obj = new CRUDOperationsDTO();
	
//	@Autowired
//	private ChildRepository childRepository;	
	
	// get all parents
	@GetMapping("/getAll")
	public List<Parent> getAllData(){
		obj.displayAll(repository);
		return repository.findAll();
	}		
	
	// create parent rest api
	@PostMapping("/createParent")
	public Parent createParent(@RequestBody Parent parent) {
		return repository.save(parent);
	}
	
	// get parent by id rest api
	@GetMapping("/parent/{id}")
	public ResponseEntity<Parent> getParentById(@PathVariable Long id) {
		Parent parent = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Data not exist with id :" + id));
		return ResponseEntity.ok(parent);
	}
	
	// update data rest api	
	@PutMapping("/parent/{id}")
	public ResponseEntity<Parent> updateData(@PathVariable Long id, @RequestBody Parent parentDetails){
		Parent parent = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Data not exist with id :" + id));
		
		parent.setName(parentDetails.getName());
		parent.setAttr(parentDetails.getAttr());		
		
		Parent updatedParent = repository.save(parent);
		return ResponseEntity.ok(updatedParent);
	}
	
	// delete data rest api
	@DeleteMapping("/parent/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteData(@PathVariable Long id){
		Parent parent = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Data not exist with id :" + id));
		
		repository.delete(parent);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}	
	
}
