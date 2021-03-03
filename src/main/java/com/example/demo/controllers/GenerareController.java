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
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entities.Client;
import com.example.demo.entities.Contact;
import com.example.demo.services.ClientServiceForBrowser;
import com.example.demo.services.ContactService;

@Controller
public class GenerareController {
	
	private Contact contactM;
	

	
	private void setContact(Contact _contact) {
		this.contactM=_contact;
	}
	
	private Contact getContact() {
		return contactM;
	}

	@Autowired
	private ContactService contactService;
	
	
	@Autowired
	private ClientServiceForBrowser clientService;
	
	@RequestMapping("/call")
	public String getAll(Model model,String keyword) {
		Contact contact=new Contact();
		model.addAttribute("contact",contact);
		return "call";
	}
	
	/*@RequestMapping("/newContact")
	public String showNewClientForm(Model model) {
		Contact contact=new Contact();
		model.addAttribute("contact",contact);
		return "new_contact";
	}*/
	
	@RequestMapping("/saveContact")
	//, method = RequestMethod.POST)
	public String saveClient(Model model,@ModelAttribute("contact") Contact contact) {
	
		
		//contact.setStart_date(new SimpleDateFormat("2020/07/31 23:59:00").format(Calendar.getInstance().getTime()));
		contact.setStart_date(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
		if(contact.getAppellant_number().substring(0, 3).equals(contact.getReceiver_number().substring(0, 3)))
			contact.setSameNetwork(true);
		else
			contact.setSameNetwork(false);
	    setContact(contact);
	    
	    List<Contact> listContacts=contactService.findByNumber(contact.getAppellant_number());
	    model.addAttribute("listContacts",listContacts);
	     
	    return "asteptare_contact";
	}
	
	@RequestMapping(value = "/contactFinalizat", method = RequestMethod.POST)
	public String finalizareApel(@ModelAttribute("contact") Contact contact) {
		
		getContact().setEnd_date(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
		
		
		String[] start=getContact().getStart_date().split("/");
		String[] end=getContact().getEnd_date().split("/");
		int start_month=Integer.parseInt(start[1]);
		int end_month=Integer.parseInt(end[1]);
		int end_year=Integer.parseInt(end[0]);
		if(start_month!=end_month) {
			Contact contact2=new Contact(getContact().getAppellant_number(),getContact().getReceiver_number());
			
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
			
			Date d1 = null;
			Date d2 = null;
				try {
				    d1 = format.parse(getContact().getStart_date());
				    d2 = format.parse(((end[0].trim().equals("2021".trim()))?"2021":"2020")+"/"+end[1]+"/01 23:59:59");
				} catch (ParseException e) {
				    e.printStackTrace();
				} 
				long diff = d2.getTime() - d1.getTime();
				int diffSeconds = (int) (diff / 1000);
				
				contact2.setStart_date(((end[0].trim().equals("2021".trim()))?"2021":"2020")+"/"+end[1]+"/01 00:00:00");
				
				contact2.setEnd_date(getContact().getEnd_date());
				contact2.setSameNetwork(getContact().isSameNetwork());
				contactService.saveContact(contact2);
				
				
				
				getContact().setEnd_date("2020/"+start[1]+"/"+start[2].substring(0, 2)+" 23:59:59");
				
				System.out.println(contact2.toString());
				System.out.println(getContact().toString());
				
			
		}
		
		
		
		contactService.saveContact(getContact());
		setContact(null);
	     
	    return "redirect:/index";
	}
	
	@GetMapping("/a")
	public String testare() {
		return "testf";
	}
	
}
	

