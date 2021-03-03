package com.example.demo.ehcache;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entities.Client;
import com.example.demo.entities.ExtraCharge;
import com.example.demo.entities.InternetTraffic;
import com.example.demo.entities.Subscription;


@Controller
@RequestMapping("/student")
public class ApiController {

	private Optional<Student> subF;
	
	private InternetTraffic newUser=null;
	
	private Optional<Student> getClient() {
		return subF;
	}
	
	private void setClient(Optional<Student> sub) {
		this.subF=sub;
	}
	
	@Autowired
	private APIService aPIService;

	@GetMapping()
	public ResponseEntity<Student> fetchStudent(@RequestParam(name = "studentId") Integer studentId,
			@RequestParam(name = "isCacheable") boolean isCacheable) throws InterruptedException {
		return new ResponseEntity<>(aPIService.fetchStudent(studentId, isCacheable).get(), HttpStatus.OK);
	}
	
	@RequestMapping("/getAll")
	public String getAll(Model model,String keyword) throws InterruptedException {
		List<Student> listStudents=aPIService.getAllStudents();
		model.addAttribute("listStudents",listStudents);
		
		String username="Sebastian";
		
		
		model.addAttribute("username",username);
		if(keyword!=null) {
			//model.addAttribute("listStudents",aPIService.findByKeyword(keyword));
		}else {
			model.addAttribute("listStudents",listStudents);
		}
		
		
		//InternetTraffic user=new InternetTraffic(userService.getUser());
		//user.setStart_date_traffic(new SimpleDateFormat("2020/07/31 23:59:00").format(Calendar.getInstance().getTime()));
		
		
		
		//newUser=user;
		
		return "students";
	}
	
	@RequestMapping("/getOne")
	@ResponseBody
	public Optional<Student> getOne(Integer id) {
		return aPIService.getStudent(id);
	}
	
	@PostMapping("/addNew")
	public String addNew(Student student) {
		aPIService.addNew(student);
		return "redirect:/student/getAll";
	}
	
	@RequestMapping(value="/update", method = {RequestMethod.PUT, RequestMethod.GET})
	public String update(Student student) {
		
		
		aPIService.update(student);
		return "redirect:/student/getAll";
	}
	
	@RequestMapping(value="/delete", method = {RequestMethod.DELETE, RequestMethod.GET})	
	public String delete(Integer id) {
		aPIService.delete(id);
		return "redirect:/student/getAll";
	}
	
	@RequestMapping("/subscriptions/{id}")
	public ModelAndView showSubscriptionClientPage(@PathVariable(name = "id") int id,Model model) {
	    ModelAndView mav = new ModelAndView("subscription_client");
	    Optional<Student> client = aPIService.getStudent(id);
	    
	    Subscription sub=client.get().getSubscription();
	    List<Subscription> listSubscriptions=new ArrayList<Subscription>();
	    listSubscriptions.add(sub);
	    
	    setClient(client);
	    mav.addObject("client", client);
	    model.addAttribute("listSubscriptions",listSubscriptions);
	    
	    
	     
	    return mav;
	}
	
	@RequestMapping("/charges/{id}")
	public ModelAndView showChargesClientPage(@PathVariable(name = "id") int id,Model model) {
	    ModelAndView mav = new ModelAndView("charges_client");
	    Optional<Student> client = aPIService.getStudent(id);
	    
	    ExtraCharge extra=client.get().getExtraCharge();
	    List<ExtraCharge> listCharges=new ArrayList<ExtraCharge>();
	    listCharges.add(extra);
	    
	    setClient(client);
	    mav.addObject("client", client);
	    model.addAttribute("listCharges",listCharges);
	    
	    
	     
	    return mav;
	}
	
	
	
	
	
	@RequestMapping(value = "/subscriptions/saveSub", method = RequestMethod.POST)
	//@ModelAttribute("client") Client client,
	public String saveSubscription(@ModelAttribute("subscription") Subscription subscription) {
	   
	    
	    //client.getComments().add(subscription);
		//getClient().getComments().add(subscription);
	     Student client=getClient().get();
	     client.setSubscription(subscription);
	     aPIService.update(client);
		
	     return "redirect:/clients/getAll";
	}
	
	@RequestMapping("/editSub/{id}")
	public ModelAndView showEditSubscriptionPage(@PathVariable(name = "id") int id) {
		Optional<Student> client=getClient();
	    ModelAndView mav = new ModelAndView("edit_subscription");
	    Subscription subscription = client.get().getSubscription();
	    mav.addObject("subscription", subscription);
	     
	    return mav;
	}
	
}