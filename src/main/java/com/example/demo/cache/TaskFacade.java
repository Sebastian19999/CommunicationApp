package com.example.demo.cache;

import java.util.Arrays;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Client;

@Service
public class TaskFacade {

	@Cacheable("tasks")
	public List<Client> findAll() {
        System.out.println("Retrieving tasks");
        return Arrays.asList(
            new Client("Mira Robert","Strada Universitatii","074367938",1300),
            new Client("Florescu Mihai","Strada Universitatii","0743829200",2000));
    }
	
}
