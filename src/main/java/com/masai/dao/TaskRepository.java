package com.masai.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	
}

