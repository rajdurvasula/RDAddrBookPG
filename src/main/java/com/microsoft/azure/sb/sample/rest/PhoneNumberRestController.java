package com.microsoft.azure.sb.sample.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microsoft.azure.sb.sample.data.Contact;
import com.microsoft.azure.sb.sample.data.ContactRepository;
import com.microsoft.azure.sb.sample.data.PhoneNumber;
import com.microsoft.azure.sb.sample.data.PhoneNumberRepository;

@RestController
@RequestMapping("/api")
public class PhoneNumberRestController {

	@Autowired
	ContactRepository contactRepo;
	
	@Autowired
	PhoneNumberRepository phoneNumberRepo;
	
	@GetMapping("/contacts/{contactId}/phoneNumbers")
	public List<PhoneNumber> getPhoneNumbersByContactId(@PathVariable(value="contactId") Long contactId) {
		if (contactRepo.existsById(contactId)) {
			return phoneNumberRepo.findByContactId(contactId);
		} else {
			throw new ResourceNotFoundException("Contact", "Id", contactId);
		}
	}
	
	@PostMapping("/contacts/{contactId}/phoneNumbers")
	public PhoneNumber addPhoneNumberToContact(@PathVariable(value="contactId") Long contactId, @Valid @RequestBody PhoneNumber phoneNumber) {
		Optional<Contact> optionalObj = contactRepo.findById(contactId);
		if (optionalObj.isPresent()) {
			Contact contact = optionalObj.get();
			phoneNumber.setContact(contact);
			return phoneNumberRepo.save(phoneNumber);
		} else {
			throw new ResourceNotFoundException("Contact", "Id", contactId);
		}
	}
	
	@GetMapping("/contacts/{contactId}/phoneNumbers/{phoneNumberId}")
	public Resource<PhoneNumber> getPhoneNumber(@PathVariable(value="contactId") Long contactId, @PathVariable(value="phoneNumberId") Long phoneNumberId) {
		Optional<Contact> optionalContact = contactRepo.findById(contactId);
		if (optionalContact.isPresent()) {
			Optional<PhoneNumber> optionalPhoneNumber = phoneNumberRepo.findById(phoneNumberId);
			if (optionalPhoneNumber.isPresent()) {
				Resource<PhoneNumber> resource = new Resource<PhoneNumber>(optionalPhoneNumber.get());
				return resource;
			} else {
				throw new ResourceNotFoundException("PhoneNumber", "Id", phoneNumberId);
			}
		} else {
			throw new ResourceNotFoundException("Contact", "Id", contactId);
		}
	}
	
	@PutMapping("/contacts/{contactId}/phoneNumbers")
	public Resource<PhoneNumber> updatePhoneNumber(@PathVariable(value="contactId") Long contactId, @Valid @RequestBody PhoneNumber phoneNumber) {
		Optional<Contact> optionalContact = contactRepo.findById(contactId);
		if (optionalContact.isPresent()) {
			Optional<PhoneNumber> optionalPhoneNumber = phoneNumberRepo.findById(phoneNumber.getId());
			if (optionalPhoneNumber.isPresent()) {
				phoneNumber.setContact(optionalContact.get());
				PhoneNumber updatedPhoneNumber = phoneNumberRepo.save(phoneNumber);
				Resource<PhoneNumber> resource = new Resource<PhoneNumber>(updatedPhoneNumber);
				return resource;
			} else {
				throw new ResourceNotFoundException("PhoneNumber", "Id", phoneNumber.getId());
			}
		} else {
			throw new ResourceNotFoundException("Contact", "Id", contactId);
		}
	}
	
	@DeleteMapping("/contacts/{contactId}/phoneNumbers/{phoneNumberId}")
	public void deletePhoneNumber(@PathVariable(value="contactId") Long contactId, @PathVariable(value="phoneNumberId") Long phoneNumberId) {
		Optional<Contact> optionalContact = contactRepo.findById(contactId);
		if (optionalContact.isPresent()) {
			Optional<PhoneNumber> optionalPhoneNumber = phoneNumberRepo.findById(phoneNumberId);
			if (optionalPhoneNumber.isPresent()) {
				phoneNumberRepo.delete(optionalPhoneNumber.get());
			} else {
				throw new ResourceNotFoundException("PhoneNumber", "Id", phoneNumberId);
			}
		} else {
			throw new ResourceNotFoundException("Contact", "Id", contactId);
		}
	}
}
