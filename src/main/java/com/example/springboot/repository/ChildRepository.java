package com.example.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.model.Child;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long>{

}
