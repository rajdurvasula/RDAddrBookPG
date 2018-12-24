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

import com.microsoft.azure.sb.sample.data.Address;
import com.microsoft.azure.sb.sample.data.AddressRepository;
import com.microsoft.azure.sb.sample.data.Contact;
import com.microsoft.azure.sb.sample.data.ContactRepository;

@RestController
@RequestMapping("/api")
public class AddressRestController {

	@Autowired
	ContactRepository contactRepo;
	
	@Autowired
	AddressRepository addressRepo;
	
	@GetMapping("/contacts/{contactId}/addresses")
	public List<Address> findAddressesByContactId(@PathVariable(value = "contactId") Long contactId) {
		if (contactRepo.existsById(contactId)) {
			return addressRepo.findByContactId(contactId);
		} else {
			throw new ResourceNotFoundException("Contact", "Id", contactId);
		}
	}
	
	@PostMapping("/contacts/{contactId}/addresses")
	public Address addAddressToContact(@PathVariable(value = "contactId") Long contactId, @Valid @RequestBody Address address) {
		Optional<Contact> optionalObj = contactRepo.findById(contactId);
		if (optionalObj.isPresent()) {
			Contact contact = optionalObj.get();
			address.setContact(contact);
			return addressRepo.save(address);
		} else {
			throw new ResourceNotFoundException("Contact", "Id", contactId);
		}
	}
	
	@GetMapping("/contacts/{contactId}/addresses/{addressId}")
	public Resource<Address> getAddress(@PathVariable(value="contactId") Long contactId, @PathVariable(value="addressId") Long addressId) {
		Optional<Contact> optionalContact = contactRepo.findById(contactId);
		if (optionalContact.isPresent()) {
			Optional<Address> optionalAddress = addressRepo.findById(addressId);
			if (optionalAddress.isPresent()) {
				Resource<Address> resource = new Resource<Address>(optionalAddress.get());
				return resource;
			} else {
				throw new ResourceNotFoundException("Address", "Id", addressId);
			}
		} else {
			throw new ResourceNotFoundException("Contact", "Id", contactId);
		}
	}
	
	@PutMapping("/contacts/{contactId}/addresses")
	public Resource<Address> updateAddress(@PathVariable(value="contactId") Long contactId, @Valid @RequestBody Address address) {
		Optional<Contact> optionalContact = contactRepo.findById(contactId);
		if (optionalContact.isPresent()) {
			Optional<Address> optionalAddress = addressRepo.findById(address.getId());
			if (optionalAddress.isPresent()) {
				address.setContact(optionalContact.get());
				Address updatedAddress = addressRepo.save(address);
				Resource<Address> resource = new Resource<Address>(updatedAddress);
				return resource;
			} else {
				throw new ResourceNotFoundException("Address", "Id", address.getId());
			}
		} else {
			throw new ResourceNotFoundException("Contact", "Id", contactId);
		}
	}
	
	@DeleteMapping("/contacts/{contactId}/addresses/{addressId}")
	public void deleteAddress(@PathVariable(value="contactId") Long contactId, @PathVariable(value="addressId") Long addressId) {
		Optional<Contact> optionalContact = contactRepo.findById(contactId);
		if (optionalContact.isPresent()) {
			Optional<Address> optionalAddress = addressRepo.findById(addressId);
			if (optionalAddress.isPresent()) {
				addressRepo.delete(optionalAddress.get());
			} else {
				throw new ResourceNotFoundException("Address", "Id", addressId);
			}
		} else {
			throw new ResourceNotFoundException("Contact", "Id", contactId);
		}
	}
}
