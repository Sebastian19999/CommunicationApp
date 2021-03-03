package com.example.demo.controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entities.Chrono;
import com.example.demo.entities.Client;
import com.example.demo.entities.Contact;
import com.example.demo.entities.ExtraCharge;
import com.example.demo.entities.InternetTraffic;
import com.example.demo.entities.Invoice;
import com.example.demo.entities.SmsContact;
import com.example.demo.entities.Subscription;
import com.example.demo.services.ClientServiceForBrowser;
import com.example.demo.services.ContactService;
import com.example.demo.services.InvoiceService;
import com.example.demo.services.SmsContactService;
import com.example.demo.services.UserService;

@Controller
public class ChronoController {

	public static String numar_introdus;
	
	@Autowired
	private ClientServiceForBrowser clientService;
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private SmsContactService smsContactService;
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@RequestMapping("/chrono")
	public String testare1(Model model) {
		List<Client> listClients=clientService.getAllClients();
		
		model.addAttribute("client", new Client());
		model.addAttribute("listClients",listClients);
		return "asteptare_chrono";
	}
	
	@RequestMapping("/chrono_wait")
	public String intrerupere(@ModelAttribute("client") Client client) {
	
		System.out.println("TRAFFFFFFIC "+client.getPhone());
		
		numar_introdus=client.getPhone().trim();
		
		return "redirect:/getChrono";
	}
	
	@GetMapping("/getChrono")
	public String getChrono(Model model) {
		
		Client client=clientService.findClientByPhoneNumber(numar_introdus).get(0);
		
		System.out.println(client.getPhone());
		
		Chrono chrono=new Chrono(client.getId(),client.getSubscription().getNetwork_minutes_included(),client.getSubscription().getMinutes_included(),
				client.getSubscription().getNetwork_sms_included(),client.getSubscription().getSms_included(),client.getSubscription().getTraffic_included(),client.getBalance());
		
		int i=Integer.parseInt(new SimpleDateFormat("MM").format(Calendar.getInstance().getTime()));
		
		List<Contact> listContacts=contactService.findByNumberAndMonth(numar_introdus.trim(),"2020/"+((i<10)?("0"+Integer.toString(i)):Integer.toString(i))+"/");
		List<SmsContact> listSmsContacts=smsContactService.findByNumberAndMonth(numar_introdus.trim(),Integer.toString(i));
		List<InternetTraffic> listTraffic=userService.findByNumberAndMonth(numar_introdus.trim(),"2020/"+((i<10)?("0"+Integer.toString(i)):Integer.toString(i))+"/");
		
		
		
		
		double total_seconds_in=0,total_seconds_out=0;
		int total_sms_in=0,total_sms_out=0;
		double total_traffic_seconds=0;
		
		int minutes_in=0;
		int minutes_out=0;
		
		
		for(Contact contact : listContacts) {
			if(contact.isSameNetwork()==true)
				minutes_in+=invoiceService.time_difference(contact.getStart_date(), contact.getEnd_date());
			else
				minutes_out+=invoiceService.time_difference(contact.getStart_date(), contact.getEnd_date());
		}
		
		
		
		for(SmsContact contact : listSmsContacts) {
			if(contact.isSameNetwork()==true) {
				total_sms_in+=contact.getSms_number();
				System.out.println(contact.toString());
			}
			else
				total_sms_out+=contact.getSms_number();
		}
		
		for(InternetTraffic contact : listTraffic) {
			System.out.println(contact.toString());
			total_traffic_seconds+=userService.calculareMbTraffic(contact.getStart_date_traffic(), contact.getEnd_date_traffic());
			System.out.println(total_traffic_seconds);
		}
		
		int minutes_traffic=(int) (total_traffic_seconds);
		
		
		model.addAttribute("minutes_in",minutes_in);
		model.addAttribute("minutes_out",minutes_out);
		model.addAttribute("total_sms_in",total_sms_in);
		model.addAttribute("total_sms_out",total_sms_out);
		model.addAttribute("minutes_traffic",minutes_traffic);
		
		
		System.out.println(minutes_in);
		System.out.println(minutes_out);
		System.out.println(total_sms_in);
		System.out.println(total_sms_out);
		System.out.println(minutes_traffic);

		
		Subscription subscription=client.getSubscription();
		ExtraCharge extraCharge=client.getExtraCharge();
		
		double monthly_cost=subscription.getMonthly_cost();
		
		double network_minutes_charge=0,minutes_charge=0;
		
		if(minutes_in>subscription.getNetwork_minutes_included()) {
			int var=0-(minutes_in-subscription.getNetwork_minutes_included());
			network_minutes_charge=Math.abs(var*extraCharge.getNetwork_call());
			
		}
		chrono.setNo_minutes_in(subscription.getNetwork_minutes_included()-minutes_in);
		chrono.setNo_balance(chrono.getNo_balance()-network_minutes_charge);
		
		if(minutes_out>subscription.getMinutes_included()) {
			int var=0-(minutes_out-subscription.getMinutes_included());
			minutes_charge=Math.abs(var*extraCharge.getCall());
			
		}
		chrono.setNo_minutes_out(subscription.getMinutes_included()-minutes_out);
		chrono.setNo_balance(chrono.getNo_balance()-minutes_charge);
		
		model.addAttribute("monthly_cost",monthly_cost);
		model.addAttribute("network_minutes_charge",network_minutes_charge);
		model.addAttribute("minutes_charge",minutes_charge);
		
		double network_sms_charge=0,sms_charge=0;
		
		if(total_sms_in>subscription.getNetwork_sms_included()) {
			int var=0-(total_sms_in-subscription.getNetwork_sms_included());
			network_sms_charge=Math.abs(var*extraCharge.getNetwork_sms());
			
		}
		chrono.setNo_sms_in(subscription.getNetwork_sms_included()-total_sms_in);
		chrono.setNo_balance(chrono.getNo_balance()-network_sms_charge);
		
		if(total_sms_out>subscription.getSms_included()) {
			int var=0-(total_sms_out-subscription.getSms_included());
			sms_charge=Math.abs(var*extraCharge.getSms());
			
		}
		chrono.setNo_sms_out(subscription.getSms_included()-total_sms_out);
		chrono.setNo_balance(chrono.getNo_balance()-sms_charge);
		
		model.addAttribute("network_sms_charge",network_sms_charge);
		model.addAttribute("sms_charge",sms_charge);
		
		double traffic_charge=0;
		
		if(minutes_traffic>subscription.getTraffic_included()) {
			int var=(int) (0-(minutes_traffic-subscription.getTraffic_included()));
			traffic_charge=Math.abs(var*extraCharge.getInternet_traffic());
			
		}
		
		chrono.setNo_traffic(subscription.getTraffic_included()-minutes_traffic);
		chrono.setNo_balance(chrono.getNo_balance()-traffic_charge);
		
		model.addAttribute("traffic_charge",traffic_charge);
		
		model.addAttribute("client",client);	
		
		model.addAttribute("chrono",chrono);
		
		
		return "chrono_page";
	}
	
}
