package com.example.demo.quartz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuartzService {

	@Autowired
	private QuartzRepository quartzRepository;
	
	public List<QuartzEntity> getAll(){
		return quartzRepository.findAll();
	}
	
	public QuartzEntity save(QuartzEntity qe) {
		return quartzRepository.save(qe);
	}
	
	public void clear() {
		quartzRepository.deleteAll();
	}
 	
}
