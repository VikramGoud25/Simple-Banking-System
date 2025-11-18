package com.bank.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerStatementDTO {

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
