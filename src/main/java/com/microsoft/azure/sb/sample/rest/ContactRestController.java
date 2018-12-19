package com.microsoft.azure.sb.sample.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microsoft.azure.sb.sample.data.Contact;
import com.microsoft.azure.sb.sample.data.ContactRepository;

@RestController
@RequestMapping("/api")
public class ContactRestController {

	@Autowired
	ContactRepository contactRepo;
	
	@GetMapping("/contacts")
	public List<Contact> getAllContacts() {
		return contactRepo.findAll();
	}
	
	@PostMapping("/contacts")
	public Contact addContact(@Valid @RequestBody Contact contact) {
		return contactRepo.save(contact);
	}
	
	@GetMapping("/contacts/{contactId}")
	public Resource<Contact> getContactById(@PathVariable(value="contactId") Long contactId) {
		Optional<Contact> optionalObj = contactRepo.findById(contactId);
		if (optionalObj.isPresent()) {
			Resource<Contact> resource = new Resource<Contact>(optionalObj.get());
			return resource;
		} else {
			throw new ResourceNotFoundException("Contact", "Id", contactId);
		}
	}
}
