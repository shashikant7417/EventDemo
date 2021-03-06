package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Event;

@Repository

public interface EventRepository extends JpaRepository<Event, Integer> {
	
	
	List<Event> findByNameContaining(String searchText);
	

}
