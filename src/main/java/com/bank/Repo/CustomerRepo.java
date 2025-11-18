package com.bank.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bank.Entity.CustomerEntity;

import jakarta.transaction.Transactional;

public interface CustomerRepo extends JpaRepository<CustomerEntity, Integer> {
	CustomerEntity findByCustomerEmailAndCustomerPassword(String customerEmail, String customerPassword);
//Customer Statements
	@Query("select c from CustomerEntity c where c.customerAccountNumber=?1")
	CustomerEntity findByCustomerAccountNumber(long customerAccountNumber);
//Find Customer Status
	List<CustomerEntity> findByCustomerStatus(String customerStatus);
//Find Customer Account Number And Customer Name
	CustomerEntity findByCustomerNameAndCustomerAccountNumber(String customerName, long customerAccountNumber);
//Update customer deposit amount
	@Modifying
    @Transactional
	@Query("UPDATE CustomerEntity c SET c.customerAmount = ?2 WHERE c.customerAccountNumber=?1")
	int updateCustomerAmountBYCustomerAccountNumber(long customerAccountNumber, long customerAmount);
}
