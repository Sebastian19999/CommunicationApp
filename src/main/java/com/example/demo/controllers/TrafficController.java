package com.example.demo.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entities.Client;
import com.example.demo.entities.InternetTraffic;
import com.example.demo.services.ClientServiceForBrowser;
import com.example.demo.services.UserService;

@Controller
public class TrafficController {

	
	private InternetTraffic newUser=null;

	@Autowired
	private ClientServiceForBrowser clientService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/traffic")
	public String testare1(Model model) {
		List<Client> listClients=clientService.getAllClients();
		
		model.addAttribute("client", new Client());
		model.addAttribute("listClients",listClients);
		return "traffic";
	}
	
	
	
	
	@RequestMapping("/intrerupereTraffic")
	public String intrerupere(@ModelAttribute("client") Client client) {
	
		System.out.println("TRAFFFFFFIC "+client.getPhone());
		
		InternetTraffic user=new InternetTraffic(client.getPhone().trim());
		user.setStart_date_traffic(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
		
		newUser=user;
		return "asteptare_traffic";
	}
	
	@RequestMapping("/finalizareContact")
	public String exitApp() {
		newUser.setEnd_date_traffic(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
		newUser.setTraffic_amount(userService.calculareMbTraffic(newUser.getStart_date_traffic(), newUser.getEnd_date_traffic()));
		userService.saveUser(newUser);
		
		
		
		
		
		
		
		
		String[] start=newUser.getStart_date_traffic().split("/");
		String[] end=newUser.getEnd_date_traffic().split("/");
		int start_month=Integer.parseInt(start[1]);
		int end_month=Integer.parseInt(end[1]);
		int end_year=Integer.parseInt(end[0]);
		if(start_month!=end_month) {
			InternetTraffic contact2=new InternetTraffic(newUser.getPhone_number());
			
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
			
			Date d1 = null;
			Date d2 = null;
				try {
				    d1 = format.parse(newUser.getStart_date_traffic());
				    d2 = format.parse(((end[0].trim().equals("2021".trim()))?"2021":"2020")+"/"+end[1]+"/01 00:00:00");
				} catch (ParseException e) {
				    e.printStackTrace();
				} 
				long diff = d2.getTime() - d1.getTime();
				int diffSeconds = (int) (diff / 1000);
				
				contact2.setStart_date_traffic(((end[0].trim().equals("2021".trim()))?"2021":"2020")+"/"+end[1]+"/01 00:00:00");
				contact2.setEnd_date_traffic(newUser.getEnd_date_traffic());
				contact2.setTraffic_amount(userService.calculareMbTraffic(contact2.getStart_date_traffic(),contact2.getEnd_date_traffic()));
				userService.saveUser(contact2);
				
				
				
				newUser.setTraffic_amount(userService.calculareMbTraffic(newUser.getStart_date_traffic(),
								newUser.getEnd_date_traffic())-userService.calculareMbTraffic(contact2.getStart_date_traffic(), contact2.getEnd_date_traffic()));
				newUser.setEnd_date_traffic("2020/"+start[1]+"/"+start[2].substring(0, 2)+" 23:59:59");
				
			
		}
		
		
		
		userService.saveUser(newUser);
		
		
		
		
		
		
		
		
		return "redirect:/index";
	} 
	
}
