package com.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.EventDto;
import com.entity.Event;
import com.exception.EventNotFoundException;
import com.repository.EventRepository;
import com.service.EventService;

@Service

public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<EventDto> getAllEvent() throws EventNotFoundException {
        // TODO Auto-generated method stub
        List<Event> events = eventRepository.findAll();

        if (events != null && events.size() > 0)
            return Arrays.asList(modelMapper.map(events, EventDto[].class));

        throw new EventNotFoundException("Event data not found");

    }

    @Override
    public List<EventDto> getEventLike(String searchText) throws EventNotFoundException {
        // TODO Auto-generated method stub

        List<Event> events = eventRepository.findByNameContaining(searchText);

        if (events != null && events.size() > 0)
            return Arrays.asList(modelMapper.map(events, EventDto[].class));

	    throw new EventNotFoundException("Event data not found");

    }

}
