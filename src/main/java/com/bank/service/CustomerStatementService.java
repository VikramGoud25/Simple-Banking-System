package com.bank.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.DAO.CustomerStatementDAO;
import com.bank.DTO.CustomerStatementDTO;
import com.bank.Entity.CustomerStatementEntity;

@Service
public class CustomerStatementService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	CustomerStatementDAO customerStatementDAO;

//Insert Customer Statement
	public CustomerStatementEntity insertCustomerStatementDAO(CustomerStatementDTO customerStatementDTO) {
		CustomerStatementEntity customerStatemntDetails=modelMapper.map(customerStatementDTO, CustomerStatementEntity.class);
		return customerStatementDAO.insertCustomerStatementDAO(customerStatemntDetails);
	}
//Customer Statement By Account number And password
	public List<CustomerStatementEntity> customerStatementAccountNumberAndCustomerName(long accountNumber ,String customerName) {
		return customerStatementDAO.customerStatementAccountNumberAndCustomerNameDAO(accountNumber, customerName);
	}
}
