package com.bank.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;

	private String customerName;
	private String customerEmail;
	private long customerMobileNumber;
	private String customerGender;
	private String customerAddress;
	private long customerAadharNumber;
	private long customerAccountNumber;
	private String customerAccountType;
	private long customerAmount;
	private String customerPassword;
	private String customerStatus;
}