package com.microsoft.azure.sb.sample.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

	List<Address> findByContactId(Long contactId);
}
