package com.bank.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bank.Entity.CustomerEntity;
import com.bank.Repo.CustomerRepo;

@Repository
public class CustomerDAO {

	@Autowired
	CustomerRepo customerRepo;

//Customer Login
	public CustomerEntity customerLoginDAO(String customerEmail, String customerPassword) {
		return customerRepo.findByCustomerEmailAndCustomerPassword(customerEmail, customerPassword);
	}

//Customer Registration
	public CustomerEntity customerRegistrationDAO(CustomerEntity customerEntity) {
		return customerRepo.save(customerEntity);
	}
	
//Customer Account Number Check
	public CustomerEntity generateAccountNumber(long accountNumber) {
		return customerRepo.findByCustomerAccountNumber(accountNumber);
	}
	
//Print all pending customer account
	public List<CustomerEntity> allPendingCustomerAccoountDAO(String customerStatus) {
		return customerRepo.findByCustomerStatus(customerStatus);
	}
	
//Find Customer Account Number And Customer Name
	public CustomerEntity checkCustomerNameAndCustomerAccountNumberDAO(String customerName, long customerAccountNumber) {
		return customerRepo.findByCustomerNameAndCustomerAccountNumber(customerName, customerAccountNumber);
	}

//Update Customer Amount BY Customer AccountNumber
	public int updateCustomerAmountBYCustomerAccountNumberDAO(long customerAccountNumber, long customerAmount) {
		return customerRepo.updateCustomerAmountBYCustomerAccountNumber(customerAccountNumber, customerAmount);
	}
	
}
