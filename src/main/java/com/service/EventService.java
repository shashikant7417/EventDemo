package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dto.EventDto;
import com.exception.EventNotFoundException;

@Service
public interface EventService {

	List<EventDto> getAllEvent() throws EventNotFoundException;
	
	List<EventDto> getEventLike(String searchText) throws EventNotFoundException;
	
	
	
}
