package com.example.demo.ehcache;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Client;
import com.example.demo.entities.ExtraCharge;
import com.example.demo.entities.Subscription;


@Service
public class APIService {

	@Autowired
	private StudentRepository studentRepository;
	
	@CacheEvict(value="twenty-second-cache", key = "'StudentInCache'+#studentId",
			condition = "#isCacheable == null || !#isCacheable", beforeInvocation = true)
	@Cacheable(value="twenty-second-cache", key = "'StudentInCache'+#studentId", 
			condition = "#isCacheable != null && #isCacheable")
	public Optional<Student> fetchStudent(Integer studentId, boolean isCacheable) throws InterruptedException {
		Thread.sleep(4000);
		return studentRepository.findById(studentId);
	}
	
	
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
				
	}
	
	public Optional<Student> getStudent(Integer id) {
		return studentRepository.findById(id);
	}


	public void addNew(Student student) {
		Subscription defaultSub=new Subscription("Student Default Subscription",20,20,15,10,20,30);
		ExtraCharge defaultExtra=new ExtraCharge("Student Default Charge",1,0.5,1.5,1,2);
		student.setSubscription(defaultSub);
		student.setExtraCharge(defaultExtra);
		
		
		studentRepository.save(student);
	}


	public void update(Student student) {
		Subscription defaultSub=new Subscription("Student Default Subscription",20,20,15,10,20,30);
		ExtraCharge defaultExtra=new ExtraCharge("Student Default Charge",1,0.5,1.5,1,2);
		student.setSubscription(defaultSub);
		student.setExtraCharge(defaultExtra);
		studentRepository.save(student);
		
	}


	public void delete(Integer id) {
		studentRepository.deleteById(id);
	}
}
