package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entities.ExtraCharge;
import com.example.demo.services.ExtraChargeService;

@Controller
public class ExtraChargeController {

	@Autowired
	private ExtraChargeService extraChargeService;
	
	@RequestMapping("/charges")
	public String getCharges(Model model,String keyword) {
			List<ExtraCharge> listClients=extraChargeService.getAllCharges();
			model.addAttribute("listClients",listClients);
			
			String username="Sebastian";
			
			
			model.addAttribute("username",username);
			
			
			return "charges";
	}
	
}
