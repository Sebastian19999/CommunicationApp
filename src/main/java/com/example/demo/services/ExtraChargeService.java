package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.ExtraCharge;
import com.example.demo.repositories.ExtraChargeRepository;

@Service
public class ExtraChargeService {

	@Autowired
	private ExtraChargeRepository extraChargeRepository;
	
	public List<ExtraCharge> getAllCharges(){
		return extraChargeRepository.findAll();
	}
	
}
