package com.bank.Entity;

import java.time.LocalDate;

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
public class CustomerStatementEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerStatementId;
	private int customerId;
	
	private long customerStatementAccountNumber;
	private String customerStatementName;
	private String customerStatementType;
	private long customerTotalAmount;
	private long customerAmount;
	private LocalDate customerStatementDate;
	private String customerStatementStaus;
}
