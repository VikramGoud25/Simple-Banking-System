package com.bank.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bank.Entity.CustomerStatementEntity;
import com.bank.Repo.CustomerStatementRepo;

@Repository
public class CustomerStatementDAO {
	
	@Autowired
	CustomerStatementRepo customerStatementRepo;
	
//Insert Customer Statement
	public CustomerStatementEntity insertCustomerStatementDAO(CustomerStatementEntity customerStatementEntity) {
		return customerStatementRepo.save(customerStatementEntity);
	}
	
//Customer Statement By Account number And password
	public List<CustomerStatementEntity> customerStatementAccountNumberAndCustomerNameDAO(long accountNumber, String customerName) {
		return customerStatementRepo.findByCustomerStatementAccountNumberAndCustomerStatementName(accountNumber, customerName);
	}
}
