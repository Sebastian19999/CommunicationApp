package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controllers.DateClientController;
import com.example.demo.entities.SmsContact;
import com.example.demo.services.ClientServiceForBrowser;
import com.example.demo.services.SmsContactService;

@RestController
@CrossOrigin
public class UsersController {

	   @Autowired
	    private ClientServiceForBrowser clientServiceForBrowser;
	   
	   @Autowired
	   private SmsContactService smsContactService;
	
    @GetMapping("/registration/{userName}")
    public ResponseEntity<Void> register(@PathVariable String userName) {
        System.out.println("handling register user request: " + userName);
        
        SmsContact sms=new SmsContact(DateClientController.numar_introdus.trim());
        sms.setReceiver_number_sms(userName.trim());
        if(userName.trim().substring(0, 3).equals(DateClientController.numar_introdus.trim().substring(0, 3)))
        	sms.setSameNetwork(true);
        else
        	sms.setSameNetwork(false);
        
        System.out.println("Creare conexiune noua: "+DateClientController.numar_introdus.trim()+", "+userName.trim());
        
        //System.out.println(smsContactService.findSmsContactByPhoneNumbers(DateClientController.numar_introdus.trim(), userName.trim()).get(0).toString());
        
        //if(smsContactService.findSmsContactByPhoneNumbersAndMonth(DateClientController.numar_introdus.trim(), userName.trim(),
        		//Integer.toString(Integer.parseInt(new SimpleDateFormat("MM").format(Calendar.getInstance().getTime())))).size()==0) {
        
        	sms.setCurrent_month(Integer.toString(Integer.parseInt(new SimpleDateFormat("MM").format(Calendar.getInstance().getTime()))));
        	
        	//sms.setCurrent_month("7");
        	
        	sms.setStart_date_sms(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
        	sms.setEnd_date_sms(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
        	System.out.println("Numarul a fost creat");
        	smsContactService.saveSmsContact(sms);
        //}
        	
        try {
            UserStorage.getInstance().setUser(userName);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        
        return ResponseEntity.ok().build();
    }

    @GetMapping("/fetchAllUsers")
    public Set<String> fetchAll() {
        return UserStorage.getInstance().getUsers();
    }
}
