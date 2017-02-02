package com.tryout.services;

import java.util.HashMap;

/**
 * Created by patrickackerman on 1/6/17.
 */
public class ServiceRegistry {
    private static HashMap<String, Service> registry = new HashMap<>();

    public void registerService(Service service){
        System.out.println(service.getClass().toString());
        String name = service.getClass().getSimpleName();
        System.out.println(name);
        registry.put(name, service);
    }

    public Service getService(String serviceName){
        return registry.get(serviceName);
    }
}
