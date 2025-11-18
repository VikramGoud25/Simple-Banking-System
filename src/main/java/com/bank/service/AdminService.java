package com.bank.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.DAO.AdminDAO;
import com.bank.DTO.AdminDTO;
import com.bank.DTO.CustomerDTO;
import com.bank.Entity.AdminEntity;
import com.bank.Entity.CustomerEntity;
import com.bank.Entity.CustomerStatementEntity;

@Service
public class AdminService {
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	AdminDAO adminDAO;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	CustomerStatementService customerStatementService;
	
//Admin Login
	public AdminEntity adminLoginService(AdminDTO adminDTO) {
		return adminDAO.adminLoginDAO(adminDTO.getAdminEmail(), adminDTO.getAdminPassword());
	}
//Admin Registration
	public AdminEntity adminRegistrationService(AdminDTO adminDTO) {
		AdminEntity adminDetails=modelMapper.map(adminDTO, AdminEntity.class);
		return adminDAO.adminRegistration(adminDetails);
	}

//Print all pending customer account
	public List<CustomerEntity> allPendingCustomerAccoountService(String customerStatus) {
		return customerService.allPendingCustomerAccoountService(customerStatus);
	}
	
//Add Customer
	public CustomerEntity customerRegistrationService(CustomerDTO customerDTO) {
		return customerService.customerRegistrationService(customerDTO);
	}
//Customer Statement 
	public List<CustomerStatementEntity> customerStatementByAccountNumberAndCutomerName(long accountNumber, String customerName) {
		return customerStatementService.customerStatementAccountNumberAndCustomerName(accountNumber, customerName);
	}
}
