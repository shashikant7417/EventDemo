package com.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dto.EventDto;
import com.exception.EventNotFoundException;
import com.service.EventService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
class EventControllerTest {

    @Mock
    private EventService eventService;

    List<EventDto> events;

    EventDto event;

    String testEventName;

    String errorMsg;

    EventNotFoundException exception;

    @BeforeEach
    public void setUp(){
        eventService = mock(EventService.class);
        events=new ArrayList<>();
        event=new EventDto();
        testEventName="Test Event";
        event.setId(1);
        event.setName(testEventName);
        events.add(event);
        errorMsg="Test event not found";
        exception=new EventNotFoundException(errorMsg);
    }

    @Test
    void testGetAllEventWithValidData() throws EventNotFoundException {
        when(eventService.getAllEvent()).thenReturn(events);
        List<EventDto> eventList=eventService.getAllEvent();
        Assert.assertEquals(eventList.size(),1);
        Assert.assertEquals(eventList.get(0).getName(),testEventName);

    }

    @Test
    void testGetAllEventWithNoData(){
        try {
            when(eventService.getAllEvent()).thenThrow(exception);
            List<EventDto> eventList = eventService.getAllEvent();
        }
        catch (EventNotFoundException e){
            Assert.assertEquals(e.getMessage(),errorMsg);
        }

    }

    @Test
    void testGetEventLikeWithValidData() throws EventNotFoundException {
        String testSearched="Test";
        when(eventService.getEventLike(testSearched)).thenReturn(events);
        List<EventDto> eventList=eventService.getEventLike(testSearched);
        Assert.assertEquals(eventList.size(),1);
        Assert.assertEquals(eventList.get(0).getName(),testEventName);
    }

    @Test
    void testGetEventLikeWithNoData(){
        String testSearched="Test";
        try {
            when(eventService.getEventLike(testSearched)).thenThrow(exception);
            List<EventDto> eventList=eventService.getAllEvent();
        }
        catch (EventNotFoundException e){
            Assert.assertEquals(e.getMessage(),errorMsg);
        }

    }
}