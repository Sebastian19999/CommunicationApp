package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Contact;
import com.example.demo.repositories.ContactRepository;

@Service
public class ContactService {
	
	@Autowired
	private ContactRepository contactRepository;
	
	public List<Contact> getAllContacts(){
		return contactRepository.findAll();
	}
	
	public Contact saveContact(Contact contact) {
		return contactRepository.save(contact);
	}

	public List<Contact> findByNumber(String keyword){
		return contactRepository.findByNumber(keyword);
	}

	public List<Contact> findByNumberAndMonth(String keyword,String month){
		return contactRepository.findByNumberAndMonth(keyword, month);
	}
	
	public List<Contact> findInner(String date2, String date3){
		return contactRepository.findInner(date2, date3);
	}
	
}
