package com.microsoft.azure.sb.sample.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {

	List<PhoneNumber> findByContactId(Long contactId);
}
