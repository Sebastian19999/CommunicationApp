package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Client;
import com.example.demo.entities.Contact;
import com.example.demo.entities.SmsContact;
import com.example.demo.repositories.ContactSmsRepository;

@Service
public class SmsContactService {

	@Autowired
	private ContactSmsRepository smsContactRepository;
	
	public SmsContact saveSmsContact(SmsContact sms) {
		return smsContactRepository.save(sms);
	}
	
	public List<SmsContact> findSmsContactByPhoneNumbers(String keynumber1,String keynumber2){
		return smsContactRepository.findSmsContactByPhoneNumbers(keynumber1,keynumber2);
	}
	
	public SmsContact updateSmsContact(SmsContact sms) {
		return smsContactRepository.save(sms);
	}
	
	public List<SmsContact> findByNumber(String keyword){
		return smsContactRepository.findByNumber(keyword);
	
	}
	
	public List<SmsContact> findSmsContactByPhoneNumbersAndMonth(String keyword1,String keyword2,String keyword3){
		return smsContactRepository.findSmsContactByPhoneNumbersAndMonth(keyword1, keyword2, keyword3);
	}
	
	public List<SmsContact> findByNumberAndMonth(String keyword,String month){
		return smsContactRepository.findByNumberAndMonth(keyword, month);
	}
	
}
