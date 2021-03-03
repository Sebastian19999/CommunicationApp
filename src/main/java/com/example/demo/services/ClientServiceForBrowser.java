package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Client;
import com.example.demo.entities.ExtraCharge;
import com.example.demo.entities.Subscription;
import com.example.demo.repositories.ClientRepository;

@Service
public class ClientServiceForBrowser {
	
	@Autowired
	private ClientRepository clientRepository;
	
	public List<Client> getAllClients(){
		return clientRepository.findAll();
	}
	
	public List<Client> findByKeyword(String keyword){
		return clientRepository.findByKeyword(keyword);
	}
	
	public List<Client> findClientByPhoneNumber(String keynumber){
		return clientRepository.findClientByPhoneNumber(keynumber);
	}
	
	public Optional<Client> getClient(Integer id){
		return clientRepository.findById(id);
	}
	
	public void addNew(Client client) {
		
		Subscription defaultSub=new Subscription("Default Subscription",10,10,20,10,10,10);
		ExtraCharge defaultExtra=new ExtraCharge("Default Extra Charge",1,0.5,1.5,1,2);
		client.setSubscription(defaultSub);
		client.setExtraCharge(defaultExtra);
		
		
		clientRepository.save(client);
	}
	
	public void update(Client client) {
		Subscription defaultSub=new Subscription("Default Subscription",10,10,20,10,10,10);
		ExtraCharge defaultExtra=new ExtraCharge("Default Extra Charge",1,0.5,1.5,1,2);
		client.setSubscription(defaultSub);
		client.setExtraCharge(defaultExtra);
		clientRepository.save(client);
	}
	
	public void delete(Integer id) {
		clientRepository.deleteById(id);
	}
	

}
