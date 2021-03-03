package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Client;
import com.example.demo.entities.ExtraCharge;
import com.example.demo.entities.Subscription;
import com.example.demo.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	public List<Client> getAllClients(){
		return clientRepository.findAll();
	}
	
	
	
	public Optional<Client> getClient(Integer id) {
		return clientRepository.findById(id);
				
	}
	
	public Client saveClient(Client client) {
		
		Subscription defaultSub=new Subscription("Default Subscription",10,10,20,10,10,10);
		ExtraCharge defaultExtra=new ExtraCharge("Default Extra Charge",1,0.5,1.5,1,2);
		client.setSubscription(defaultSub);
		client.setExtraCharge(defaultExtra);
		
		return clientRepository.save(client);
	}
	
	public Client updateClient(Integer id,Client client) {
		
		
		
		return clientRepository.save(client);
	}
	
	public String deleteClient(Integer id) {
		clientRepository.deleteById(id);
		return "Clientul a fost sters cu succes";
	}
	
	
	
}
