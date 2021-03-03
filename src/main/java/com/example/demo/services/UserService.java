package com.example.demo.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.InternetTraffic;
import com.example.demo.repositories.InternetTrafficRepository;

@Service
public class UserService {

	
	@Autowired
	private InternetTrafficRepository internetTrafficRepository;
	
	public String getUser() {
		Object user= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String[] sir=user.toString().split(" ");
		return sir[2].substring(0, sir[2].length()-1);
	}
	
	public InternetTraffic saveUser(InternetTraffic user) {
		return internetTrafficRepository.save(user);
	}
	
	public List<InternetTraffic> findByNumber(String keyword){
		return internetTrafficRepository.findByNumber(keyword);
	}
	
	public List<InternetTraffic> findByNumberAndMonth(String keyword,String month){
		return internetTrafficRepository.findByNumberAndMonth(keyword, month);
	}
	
	public int calculareMbTraffic(String dateStart,String dateStop) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  

		Date d1 = null;
		Date d2 = null;
		try {
		    d1 = format.parse(dateStart);
		    d2 = format.parse(dateStop);
		} catch (ParseException e) {
		    e.printStackTrace();
		}    
		
		long diff = d2.getTime() - d1.getTime();
		
		return (int) Math.ceil((diff / 1000)/60);
	}
	
}
