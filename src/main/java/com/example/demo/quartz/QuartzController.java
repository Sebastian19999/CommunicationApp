package com.example.demo.quartz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.WebSocketApp2Application;
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
@RequestMapping("/invoice")
public class QuartzController {

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
	
	@Autowired
	private Environment env;
	
	@RequestMapping("/gt")
	public String generateInvoicesForTheLast30Days(Model model) {
		
		
		
		System.out.println(searchForLastMonth(env.getProperty("application.quartz.updateDate")));
		

		List<Client> listClients=clientService.getAllClients();

		List<Invoice> listInvoices=new ArrayList<Invoice>();
		
		
		for(Client client : listClients) {
			
			List<Contact> contacts,listContacts=new ArrayList<Contact>();
			List<SmsContact> listSms,listSmsContacts=new ArrayList<SmsContact>();
			List<InternetTraffic> listTraffic,listInternetTraffic=new ArrayList<InternetTraffic>();
			
			contacts=contactService.findByNumber(client.getPhone().trim());
			listSms=smsContactService.findByNumber(client.getPhone().trim());
			listTraffic=userService.findByNumber(client.getPhone().trim());
			
			for(Contact contact : contacts) {
				try {
					if(comparare(contact.getStart_date(),searchForLastMonth(env.getProperty("application.quartz.updateDate")),env.getProperty("application.quartz.updateDate"))==true) {
						listContacts.add(contact);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
			for(SmsContact sms : listSms) {
				try {
					if(comparare(sms.getStart_date_sms(),searchForLastMonth(env.getProperty("application.quartz.updateDate")),env.getProperty("application.quartz.updateDate"))==true) {
						listSmsContacts.add(sms);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
			for(InternetTraffic traffic : listTraffic) {
				try {
					if(comparare(traffic.getStart_date_traffic(),searchForLastMonth(env.getProperty("application.quartz.updateDate")),env.getProperty("application.quartz.updateDate"))==true) {
						listInternetTraffic.add(traffic);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
			
			Invoice invoice=new Invoice();
			
			
			
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
				if(contact.isSameNetwork()==true)
					total_sms_in+=contact.getSms_number();
				else
					total_sms_out+=contact.getSms_number();
			}
			
			for(InternetTraffic contact : listTraffic) {
				total_traffic_seconds+=userService.calculareMbTraffic(contact.getStart_date_traffic(), contact.getEnd_date_traffic());
			}
			
			int minutes_traffic=(int) (total_traffic_seconds/60);
			
			
			

			
			Subscription subscription=client.getSubscription();
			ExtraCharge extraCharge=client.getExtraCharge();
			
			double monthly_cost=subscription.getMonthly_cost();
			
			double network_minutes_charge=0,minutes_charge=0;
			
			if(minutes_in>subscription.getNetwork_minutes_included()) {
				int var=0-(minutes_in-subscription.getNetwork_minutes_included());
				network_minutes_charge=Math.abs(var*extraCharge.getNetwork_call());
			}
			
			if(minutes_out>subscription.getMinutes_included()) {
				int var=0-(minutes_out-subscription.getMinutes_included());
				minutes_charge=Math.abs(var*extraCharge.getCall());
			}
			
			
			double network_sms_charge=0,sms_charge=0;
			
			if(total_sms_in>subscription.getNetwork_sms_included()) {
				int var=0-(total_sms_in-subscription.getNetwork_sms_included());
				network_sms_charge=Math.abs(var*extraCharge.getNetwork_sms());
			}
			
			if(total_sms_out>subscription.getSms_included()) {
				int var=0-(total_sms_out-subscription.getSms_included());
				sms_charge=Math.abs(var*extraCharge.getSms());
			}
			
			
			double traffic_charge=0;
			
			if(minutes_traffic>subscription.getTraffic_included()) {
				int var=(int) (0-(minutes_traffic-subscription.getTraffic_included()));
				traffic_charge=Math.abs(var*extraCharge.getInternet_traffic());
			}
			
			
			
			invoice.setPhone_number(client.getPhone());
			invoice.setMinutes_in(minutes_in);
			invoice.setMinutes_out(minutes_out);
			invoice.setTotal_sms_in(total_sms_in);
			invoice.setTotal_sms_out(total_sms_out);
			invoice.setMinutes_traffic(minutes_traffic);
			invoice.setMonthly_cost(monthly_cost);
			invoice.setNetwork_minutes_charge(network_minutes_charge);
			invoice.setMinutes_charge(minutes_charge);
			invoice.setNetwork_sms_charge(network_sms_charge);
			invoice.setSms_charge(sms_charge);
			invoice.setTraffic_charge(traffic_charge);
			
			
			listInvoices.add(invoice);
			
			
			
			
			System.out.println(listContacts);
		}
		
		
		model.addAttribute("listInvoices",listInvoices);
		
		return "test";
	}
	



	private boolean comparare(String date1,String date2,String date3) throws ParseException{
		
		System.out.println("Check..." + date2+" and "+date3);
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    Date d1 = sdformat.parse(date1);
	    Date d2 = sdformat.parse(date2);
	    Date d3 = sdformat.parse(date3);
	    if(d1.compareTo(d2) >= 0 && d1.compareTo(d3) <= 0) {
	    	
	       return true;
	    } 
	    return false;
	}
	
	private String searchForLastMonth(String date) {
		String[] datef=date.split(" ");
		String[] dateZ=datef[0].split("/");
		
		return dateZ[0]+"/"+((Integer.parseInt(dateZ[1])<10)?"0":"1")+Integer.toString(Integer.parseInt(dateZ[1])-1)+"/"+dateZ[2]+" "+datef[1];
	}
}