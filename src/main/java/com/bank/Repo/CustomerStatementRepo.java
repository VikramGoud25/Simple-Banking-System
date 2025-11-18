package com.bank.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.Entity.CustomerStatementEntity;

public interface CustomerStatementRepo extends JpaRepository<CustomerStatementEntity, Integer>{
	List<CustomerStatementEntity> findByCustomerStatementAccountNumberAndCustomerStatementName(long accountNumber, String customerName);
}
