package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Client;
import com.example.demo.services.ClientService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private ClientService clientService;
	
	@GetMapping("/{id}")
	public Optional<Client> getUser(@PathVariable Integer id) {
		return clientService.getClient(id);
	}
	
}
