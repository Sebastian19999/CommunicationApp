package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.cache.TaskFacade;
import com.example.demo.entities.Client;
import com.example.demo.services.ClientService;

@RestController
@RequestMapping("/management/api/users")
public class ManagementController {

	@Autowired
	private ClientService clientService;
	
	@GetMapping
	public List<Client> getAllClients(){
		return clientService.getAllClients();
	}
	
	@GetMapping("/{id}")
	public Optional<Client> getClient(@PathVariable Integer id) {
		return clientService.getClient(id);
	}
	
	@PostMapping
	public Client saveClient(@RequestBody Client client) {
		return clientService.saveClient(client);
	}
	
	@PutMapping("/update/{id}")
	public Client updateClient(@PathVariable Integer id,@RequestBody Client client) {
		return clientService.updateClient(id, client);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteClient(@PathVariable Integer id) {
		return clientService.deleteClient(id);
	}
	
	@Autowired
	 private TaskFacade taskFacade;

	    @GetMapping("/tasks")
	    public List<Client> findAll() {
	        return taskFacade.findAll();
	    }
	
}
