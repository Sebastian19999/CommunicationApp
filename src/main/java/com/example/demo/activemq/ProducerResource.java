package com.example.demo.activemq;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/publish")
public class ProducerResource {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	private Queue queue;
	
	/*@GetMapping("/inmem/{message}")
	public String publish(@PathVariable("message") final String message) {
		jmsTemplate.convertAndSend(queue,message);
		
		return "Published Successfully";
	}*/
	
	@GetMapping("/{message}")
	public String publishStandalone(@PathVariable("message") final String message) {
		jmsTemplate.convertAndSend(queue,message);
		
		return "Published Successfully";
	}
	
}
