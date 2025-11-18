package com.bank.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

	private int customerId;
	private String customerBankName;

	@NotBlank(message = "Enter Valid Name")
	private String customerName;
	@Email(message = "Enter Valid Email")
	private String customerEmail;
	@Min(value = 6999999999l, message = "Enter Vaild Mobile Number")
	@Max(value = 9999999999l, message = "Enter Less Than 10 Number")
	private long customerMobileNumber;
	@NotBlank(message = "Click Gender")
	private String customerGender;
	@NotBlank(message = "Enter Vaild Address")
	private String customerAddress;
	@Min(value = 011111111111l)
	@Max(value = 999999999999l)
	private long customerAadharNumber;
	private long customerAccountNumber;
	@NotBlank(message = "Click Account Type")
	private String customerAccountType;
	@Min(value = 1, message = "Enter the amount")
	@Max(value = 50000,message = "Enter Less Than 50000 ")
	private long customerAmount;
	@NotBlank(message = "Enter Valid Password")
	private String customerPassword;
	private String customerStatus;

}