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
public class AdminDTO {
	
	private int adminId;
	
	@NotBlank(message = "Enter Valid Admin Name")
	private String adminName;
	@Email(message = "Enter Valid Admin Email")
	private String adminEmail;
	@Min(value = 6999999999l, message = "Enter Vaild Mobile Number")
	@Max(value = 9999999999l, message = "Enter Less Than 10 Number")
	private long adminMobileNumber;
	@NotBlank(message = "Enter Valid Passowrd")
	private String adminPassword;
}
