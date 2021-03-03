package com.example.demo.controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entities.InternetTraffic;
import com.example.demo.entities.Subscription;
import com.example.demo.services.SubscriptionService;

@Controller
public class SubscriptionController {

	@Autowired
	private SubscriptionService subscriptionService;
	
	@RequestMapping("/subscriptions")
	public String getSubscriptions(Model model,String keyword) {
			List<Subscription> listClients=subscriptionService.getAllSubscriptions();
			model.addAttribute("listClients",listClients);
			
			String username="Sebastian";
			
			
			model.addAttribute("username",username);
			
			
			return "subscriptions";
	}
	
}
