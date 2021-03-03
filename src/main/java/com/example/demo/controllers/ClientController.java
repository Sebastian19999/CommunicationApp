package com.example.demo.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entities.Client;
import com.example.demo.entities.Contact;
import com.example.demo.entities.ExtraCharge;
import com.example.demo.entities.InternetTraffic;
import com.example.demo.entities.Subscription;
import com.example.demo.services.ClientServiceForBrowser;
import com.example.demo.services.UserService;

@Controller
@RequestMapping("/clients")
public class ClientController {

	private Optional<Client> subF;
	
	private InternetTraffic newUser=null;
	
	private Optional<Client> getClient() {
		return subF;
	}
	
	private void setClient(Optional<Client> sub) {
		this.subF=sub;
	}
	
	@Autowired
	private ClientServiceForBrowser clientService;
	
	@Autowired
	private UserService userService;
	
	
	
	@RequestMapping("/**")
	public String getAll(Model model,String keyword) {
		List<Client> listClients=clientService.getAllClients();
		model.addAttribute("listClients",listClients);
		
		String username="Sebastian";
		
		
		model.addAttribute("username",username);
		if(keyword!=null) {
			model.addAttribute("listClients",clientService.findByKeyword(keyword));
		}else {
			model.addAttribute("listClients",listClients);
		}
		
		
		InternetTraffic user=new InternetTraffic(userService.getUser());
		user.setStart_date_traffic(new SimpleDateFormat("2020/07/31 23:59:00").format(Calendar.getInstance().getTime()));
		
		
		
		newUser=user;
		
		return "clients";
	}
	
	@RequestMapping("/getOne")
	@ResponseBody
	public Optional<Client> getOne(Integer id) {
		return clientService.getClient(id);
	}
	
	@PostMapping("/addNew")
	public String addNew(Client client) {
		clientService.addNew(client);
		return "redirect:/clients/getAll";
	}
	
	@RequestMapping(value="/update", method = {RequestMethod.PUT, RequestMethod.GET})
	public String update(Client client) {
		
		
		clientService.update(client);
		return "redirect:/clients/getAll";
	}
	
	
	
	@RequestMapping(value="/delete", method = {RequestMethod.DELETE, RequestMethod.GET})	
	public String delete(Integer id) {
		clientService.delete(id);
		return "redirect:/clients/getAll";
	}
	
	@RequestMapping("/subscriptions/{id}")
	public ModelAndView showSubscriptionClientPage(@PathVariable(name = "id") int id,Model model) {
	    ModelAndView mav = new ModelAndView("subscription_client");
	    Optional<Client> client = clientService.getClient(id);
	    
	    Subscription sub=client.get().getSubscription();
	    List<Subscription> listSubscriptions=new ArrayList<Subscription>();
	    listSubscriptions.add(sub);
	    
	    setClient(client);
	    mav.addObject("client", client);
	    model.addAttribute("listSubscriptions",listSubscriptions);
	    
	    
	     
	    return mav;
	}
	
	@RequestMapping("/charges/{id}")
	public ModelAndView showChargesClientPage(@PathVariable(name = "id") int id,Model model) {
	    ModelAndView mav = new ModelAndView("charges_client");
	    Optional<Client> client = clientService.getClient(id);
	    
	    ExtraCharge extra=client.get().getExtraCharge();
	    List<ExtraCharge> listCharges=new ArrayList<ExtraCharge>();
	    listCharges.add(extra);
	    
	    setClient(client);
	    mav.addObject("client", client);
	    model.addAttribute("listCharges",listCharges);
	    
	    
	     
	    return mav;
	}
	
	
	
	
	
	@RequestMapping(value = "/subscriptions/saveSub", method = RequestMethod.POST)
	//@ModelAttribute("client") Client client,
	public String saveSubscription(@ModelAttribute("subscription") Subscription subscription) {
	   
	    
	    //client.getComments().add(subscription);
		//getClient().getComments().add(subscription);
	     Client client=getClient().get();
	     client.setSubscription(subscription);
	     clientService.update(client);
		
	     return "redirect:/clients/getAll";
	}
	
	@RequestMapping("/editSub/{id}")
	public ModelAndView showEditSubscriptionPage(@PathVariable(name = "id") int id) {
		Optional<Client> client=getClient();
	    ModelAndView mav = new ModelAndView("edit_subscription");
	    Subscription subscription = client.get().getSubscription();
	    mav.addObject("subscription", subscription);
	     
	    return mav;
	}
	
	@RequestMapping("/exit")
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
		
		
		
		
		
		
		
		
		return "redirect:/logout";
	}
	
}
