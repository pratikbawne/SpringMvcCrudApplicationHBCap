package com.nt.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.nt.dao.ContactDao;
import com.nt.model.Employee;




@Controller
public class MainController {
	
	@Autowired
	private ContactDao contactDao;
	
	@RequestMapping(value= "/",method=RequestMethod.GET)
	public ModelAndView newEmployee(ModelAndView model) {
		System.out.println("newEmployee() Called ");
            Employee newEmployee = new Employee();
            model.addObject("employee",newEmployee);
            model.setViewName("index");
		return model;
	}
	


	@RequestMapping(value="/emp",method=RequestMethod.POST)
	public String saveContact(@ModelAttribute Employee employee) {
		
		if(employee.getId() == null) {
		      contactDao.save(employee);
		      System.out.println("Record Saved Successfully..");
			}
		
	     return "contactForm";
	}
	
	
	@RequestMapping(value= "/contact")
	public ModelAndView listContact(ModelAndView model) {
		System.out.println("listContact method Called");
		List<com.nt.model.Contact> listContact = contactDao.list();
		model.addObject("listContact",listContact);
		model.setViewName("contactFormList");
		
		return model;
	}
	
	
	@RequestMapping(value= "/newContactForm",method=RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
            com.nt.model.Contact newContact = new com.nt.model.Contact();
            model.addObject("contact",newContact);
            model.setViewName("contact_form");
		return model;
	}
	
	

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView saveContact(@ModelAttribute com.nt.model.Contact contact) {
		
		if(contact.getId() == null) {
	      contactDao.save(contact);
		}
		else
		{
			contactDao.update(contact);
		}
	     return new ModelAndView("redirect:/contact");
	}
	
	
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest req) {
	   
		Integer id = Integer.parseInt(req.getParameter("id"));
		com.nt.model.Contact contact = contactDao.get(id);
		
		ModelAndView mav = new ModelAndView("contact_form");
		
		mav.addObject("contact", contact);
		
	     return mav;
	}
	
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public ModelAndView deleteContact(@RequestParam Integer id) {
	   
	     contactDao.delete(id);
		
	     return new ModelAndView("redirect:/contact");
	     
	}
	
	
}
