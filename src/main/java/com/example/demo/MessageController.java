package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controllers.DateClientController;
import com.example.demo.entities.SmsContact;
import com.example.demo.services.SmsContactService;

@RestController
public class MessageController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    
    @Autowired
    private SmsContactService smsContactService;
    
 

    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable String to, MessageModel message) {
        System.out.println("handling send message: " + message + " to: " + to);
        boolean isExists = UserStorage.getInstance().getUsers().contains(to);
        if (isExists) {
            simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
            System.out.println("Numar introdus: "+DateClientController.numar_introdus);
            System.out.println("Catre: "+to);
            System.out.println("Mesaj: "+message);
            System.out.println("Conexiune: "+smsContactService.findSmsContactByPhoneNumbers(DateClientController.numar_introdus, to));
            
            
            if(smsContactService.findSmsContactByPhoneNumbersAndMonth(DateClientController.numar_introdus.trim(), to,
            		Integer.toString(Integer.parseInt(new SimpleDateFormat("MM").format(Calendar.getInstance().getTime())))).size()==0) {
            
            	 SmsContact sms=new SmsContact(DateClientController.numar_introdus.trim());
            	 
            	 sms.setReceiver_number_sms(to);
            	 if(DateClientController.numar_introdus.trim().substring(0, 3).equals(to.trim().substring(0, 3)))
                 	sms.setSameNetwork(true);
                 else
                 	sms.setSameNetwork(false);
            	 
            	 sms.setCurrent_month(Integer.toString(Integer.parseInt(new SimpleDateFormat("MM").format(Calendar.getInstance().getTime()))));
            	 sms.setStart_date_sms(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
            	 sms.setEnd_date_sms(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
            	 smsContactService.saveSmsContact(sms);
            
            }else {
            SmsContact sms=smsContactService.findSmsContactByPhoneNumbersAndMonth(DateClientController.numar_introdus.trim(), to,
            		Integer.toString(Integer.parseInt(new SimpleDateFormat("MM").format(Calendar.getInstance().getTime())))).get(0);
            System.out.println(sms.toString());
            int nr = sms.getSms_number();
            nr++;
            sms.setSms_number(nr);
            sms.setEnd_date_sms(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
            
            smsContactService.updateSmsContact(sms);
            }
        }else {
        	System.out.println("Nu exista");
        	
        }
    }
}
