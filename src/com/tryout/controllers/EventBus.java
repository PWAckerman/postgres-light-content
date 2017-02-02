package com.tryout.controllers;

import com.tryout.services.Service;

import java.util.HashMap;

/**
 * Created by patrickackerman on 1/8/17.
 */
public class EventBus implements Service{
    HashMap<String, Event> eventBus = new HashMap<>();

    public void setEvent(String key,Event event){
        eventBus.put(key, event);
    }

    public Object runEvent(String key, DataCarrier data){
        return eventBus.get(key).runEvent(data);
    }
}
