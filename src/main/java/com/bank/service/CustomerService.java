package com.bank.service;

import java.util.List;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.DAO.CustomerDAO;
import com.bank.DTO.CustomerDTO;
import com.bank.DTO.CustomerStatementDTO;
import com.bank.Entity.CustomerEntity;
import com.bank.Entity.CustomerStatementEntity;

@Service
public class CustomerService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	CustomerDAO customerDAO;

	@Autowired
	CustomerStatementService customerStatementService;

//Customer login
	public CustomerEntity customerLoginService(CustomerDTO customerDTO) {
		return customerDAO.customerLoginDAO(customerDTO.getCustomerEmail(), customerDTO.getCustomerPassword());
	}

//Customer Registration
	public CustomerEntity customerRegistrationService(CustomerDTO customerDTO) {
		while (true) {
			long generateAccNumber = generateAccountNumber();
			if (customerDAO.generateAccountNumber(generateAccNumber) == null) {
				customerDTO.setCustomerStatus("Pending");
				customerDTO.setCustomerAccountNumber(generateAccNumber);
				break;
			}
		}
		CustomerEntity details = modelMapper.map(customerDTO, CustomerEntity.class);
		return customerDAO.customerRegistrationDAO(details);
	}

// Generate a 12-digit account number
	public static long generateAccountNumber() {
		Random random = new Random();
		long base = 100000000000L; // minimum 12-digit number
		long accountNumber = base + (long) (random.nextDouble() * 899999999999L);
		return accountNumber;
	}

//Print all pending customer account

	public List<CustomerEntity> allPendingCustomerAccoountService(String customerStatus) {
		return customerDAO.allPendingCustomerAccoountDAO(customerStatus);
	}

//Find Customer Account Number And Customer Name
	public CustomerEntity checkCustomerNameAndCustomerAccountNumberService(String customerName,
			long customerAccountNumber) {
		return customerDAO.checkCustomerNameAndCustomerAccountNumberDAO(customerName, customerAccountNumber);
	}

//Update Customer Amount BY Customer AccountNumber
	public int updateCustomerAmountBYCustomerAccountNumberServiec(long customerAccountNumber, long customerAmount) {
		return customerDAO.updateCustomerAmountBYCustomerAccountNumberDAO(customerAccountNumber, customerAmount);
	}

//Insert Customer Statement
	public CustomerStatementEntity insertCustomerStatementSerivec(CustomerStatementDTO customerStatementDTO) {
		CustomerStatementEntity customerStatemntDetails=modelMapper.map(customerStatementDTO, CustomerStatementEntity.class);
		return customerStatementService.insertCustomerStatementDAO(customerStatementDTO);
	}
}
