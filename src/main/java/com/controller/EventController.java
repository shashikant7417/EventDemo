package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.EventDto;
import com.exception.EventNotFoundException;
import com.service.EventService;



@RestController
@RequestMapping("/api")
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@GetMapping("/getAllEvent")
	public List<EventDto> getAllEvent(){
		List<EventDto> eventList;
		try {
			eventList= eventService.getAllEvent();
		}
		catch (EventNotFoundException e){
			eventList=new ArrayList<>();
			EventDto emptyEvent=new EventDto();
			emptyEvent.setName(e.getMessage());
			eventList.add(emptyEvent);
		}
		return eventList;
		}

	@GetMapping("/getEventLike/{searchText}")
	public List<EventDto> getEventLike(@PathVariable String searchText){

		List<EventDto> eventList;
		try {
			eventList= eventService.getEventLike(searchText);
		}
		catch (EventNotFoundException e){
			eventList=new ArrayList<>();
			EventDto emptyEvent=new EventDto();
			emptyEvent.setName(e.getMessage());
			eventList.add(emptyEvent);
		}
		return eventList;
		}
}
