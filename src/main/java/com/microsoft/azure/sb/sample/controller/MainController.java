package com.microsoft.azure.sb.sample.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.microsoft.azure.sb.sample.data.Contact;
import com.microsoft.azure.sb.sample.data.ContactRepository;
import com.microsoft.azure.sb.sample.form.ContactForm;

@Controller
public class MainController {

	@Autowired
	ContactRepository contactRepo;
	
	@Value("${welcome.message}")
	private String message;
	
	@RequestMapping(value = { "/", "/index" }, method=RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("message", message);
		return "index";
	}
	
	@RequestMapping(value = { "/contactList" }, method=RequestMethod.GET)
	public String contactList(Model model) {
		List<Contact> contacts = contactRepo.findAll();
		model.addAttribute("contacts", contacts);
		return "contactList";
	}
	
	@RequestMapping(value = { "/addContact" }, method=RequestMethod.GET)
	public String addContactForm(Model model) {
		ContactForm contactForm = new ContactForm();
		model.addAttribute("contactForm", contactForm);
		return "addContact";
	}
	
	@RequestMapping(value = { "/addContact" }, method=RequestMethod.POST)
	public String addContactSave(Model model, @ModelAttribute("contactForm") ContactForm contactForm) {
		List<String> errorMessageList = new ArrayList<String>();
		String firstName = contactForm.getFirstName();
		String lastName = contactForm.getLastName();
		Contact contact = new Contact();
		if (firstName.trim().equals(""))
			errorMessageList.add("First Name cannot be empty !");
		contact.setFirstName(contactForm.getFirstName());
		if (lastName.trim().equals(""))
			errorMessageList.add("Last Name cannot be empty !");
		contact.setLastName(contactForm.getLastName());
		contactRepo.save(contact);
		String[] errorMessages = new String[errorMessageList.size()];
		errorMessages = (String[]) errorMessageList.toArray(new String[0]);
		if (!errorMessageList.isEmpty()) {
			model.addAttribute("errorMessages", errorMessages);
			return "addContact";
		}
		return "redirect:/contactList";
	}
}
