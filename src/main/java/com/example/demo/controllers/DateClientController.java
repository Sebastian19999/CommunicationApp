package com.example.demo.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RequestMapping("/cautareClient")
public class DateClientController {
	
	public static String numar_introdus;
	private List<Invoice> listInvoiceT;
	
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
	private ServletContext context;
	
	@RequestMapping("/")
	public String getSearchForm() {
		return "cautare_dupa_numar";
	}
	
	@RequestMapping("/getClientByPhoneNumber")
	public String getAll(Model model,String keynumber) {
		List<Client> listClients=clientService.getAllClients();
		//model.addAttribute("listClients",listClients);
		
		String username="Sebastian";
		
		
		model.addAttribute("username",username);
		if(keynumber!=null) {
			model.addAttribute("listClients",clientService.findClientByPhoneNumber(keynumber));
		}else {
			model.addAttribute("listClients",listClients);
		}
		return "clients";
		//date_contact_cautat
	}
	
	/////////////////MESSENGER/////////
	@RequestMapping("/messenger")
	public String getSearchMessForm() {
		return "cautare_dupa_numar_mess";
	}
	
	@RequestMapping("/getOne")
	//@ResponseBody
	public String getOne(String keynumber) {
		System.out.println(clientService.findClientByPhoneNumber(keynumber).get(0).toString());
		
		numar_introdus=keynumber;
		
		//if(clientService.findClientByPhoneNumber(phone).get(0)!=null)
		return "redirect:/";
		//else
			//return "cautare_dupa_numar_mess";
	}
	
	
	
	////////DISPLAY DATA API//////////
	@RequestMapping("/search")
	public String getSearchFor() {
		
		return "date_client_cautare";
	}
	
	
	
	
	@RequestMapping("/getClient")
	public String getInvoices(Model model,String keynumber) {
		
		invoiceService.clearInvoice();
		
		
		for(int i=1;i<=Integer.parseInt(new SimpleDateFormat("MM").format(Calendar.getInstance().getTime()));i++) {
			
			List<Contact> listContacts=contactService.findByNumberAndMonth(keynumber,"2020/"+((i<10)?("0"+Integer.toString(i)):Integer.toString(i))+"/");
			List<SmsContact> listSmsContacts=smsContactService.findByNumberAndMonth(keynumber,Integer.toString(i));
			List<InternetTraffic> listTraffic=userService.findByNumberAndMonth(keynumber,"2020/"+((i<10)?("0"+Integer.toString(i)):Integer.toString(i))+"/");
			
			
			
			
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
				if(contact.isSameNetwork()==true)
					total_sms_in+=contact.getSms_number();
				else
					total_sms_out+=contact.getSms_number();
			}
			
			for(InternetTraffic contact : listTraffic) {
				total_traffic_seconds+=userService.calculareMbTraffic(contact.getStart_date_traffic(), contact.getEnd_date_traffic());
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

			
			Client client=clientService.findClientByPhoneNumber(keynumber).get(0);
			
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
			
			model.addAttribute("monthly_cost",monthly_cost);
			model.addAttribute("network_minutes_charge",network_minutes_charge);
			model.addAttribute("minutes_charge",minutes_charge);
			
			double network_sms_charge=0,sms_charge=0;
			
			if(total_sms_in>subscription.getNetwork_sms_included()) {
				int var=0-(total_sms_in-subscription.getNetwork_sms_included());
				network_sms_charge=Math.abs(var*extraCharge.getNetwork_sms());
			}
			
			if(total_sms_out>subscription.getSms_included()) {
				int var=0-(total_sms_out-subscription.getSms_included());
				sms_charge=Math.abs(var*extraCharge.getSms());
			}
			
			model.addAttribute("network_sms_charge",network_sms_charge);
			model.addAttribute("sms_charge",sms_charge);
			
			double traffic_charge=0;
			
			if(minutes_traffic>subscription.getTraffic_included()) {
				int var=(int) (0-(minutes_traffic-subscription.getTraffic_included()));
				traffic_charge=Math.abs(var*extraCharge.getInternet_traffic());
			}
			
			model.addAttribute("traffic_charge",traffic_charge);
			
			model.addAttribute("client",client);		
			
			
			numar_introdus=keynumber;
			
			
			
			
			
			
			
			
			
			
			
			Invoice invoice=new Invoice();
			invoice.setCurrent_month(i);
			invoice.setPhone_number(keynumber);
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
			
			invoiceService.saveInvoice(invoice);
			
		}
		
		return "client_data";
		
		
		
	}
	
	
	
	@RequestMapping("/createPdf")
	public void createPdf(HttpServletRequest request,HttpServletResponse response) {
		List<Invoice> listInvoice=invoiceService.getAll();
		boolean isFlag=invoiceService.createPdf(listInvoice,context,request,response);
		
		if(isFlag) {
			String fullPath=request.getServletContext().getRealPath("/resources/reports/"+"listInvoice"+".pdf");
			fileDownload(fullPath,response,"listInvoice.pdf");
		
		
		}
	}
	
	private void fileDownload(String fullPath, HttpServletResponse response, String fileName) {
		File file=new File(fullPath);
		
		final int BUFFER_SIZE=4096;
		if(file.exists()) {
			try {
				FileInputStream inputStream=new FileInputStream(file);
				String mimeType=context.getMimeType(fullPath);
				response.setContentType(mimeType);
				response.setHeader("content-disposition", "attachment; filename="+fileName);
				
				OutputStream outputStream=response.getOutputStream();
			
				byte[] buffer=new byte[BUFFER_SIZE];
				int bytesRead=-1;
				while((bytesRead=inputStream.read(buffer))!=-1) {
					outputStream.write(buffer,0,bytesRead);
				}
				
				inputStream.close();
				outputStream.close();
				file.delete();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	
}
