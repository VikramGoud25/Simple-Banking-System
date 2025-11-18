package com.bank.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bank.DTO.AdminDTO;
import com.bank.DTO.CustomerDTO;
import com.bank.Entity.AdminEntity;
import com.bank.Entity.CustomerEntity;
import com.bank.Entity.CustomerStatementEntity;
import com.bank.service.AdminService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminContoller {

	@Autowired
	AdminService adminService;
//Admin Home page

	@RequestMapping("/adminhomepage")
	public String adminHomePage(AdminDTO adminDTO, Model model) {
		model.addAttribute("adminDetails", adminDTO);
		return "/AdminPages/AdminHomePage";
	}

//Admin Login
	@RequestMapping("/adminloginpage")
	public String adminHomePage() {
		return "/AdminPages/AdminLoginPage";
	}

	@RequestMapping("/adminlogin")
	public String adminLogin(@Valid AdminDTO adminDTO, Model model) {
		AdminEntity adminDetails = adminService.adminLoginService(adminDTO);
		if (adminDetails != null) {
			model.addAttribute("adminDetails", adminDetails);
			return "/AdminPages/AdminHomePage";
		} else {
			return "/AdminPages/AdminLoginPage";
		}
	}

//Admin registration 
	@RequestMapping("/adminregistrationpage")
	public String adminRegistrationPage() {
		return "/AdminPages/AdminRegistrationPage";
	}

	@RequestMapping("/adminregistration")
	public String adminregistration( @Valid AdminDTO adminDTO) {
		AdminEntity adminDetails = adminService.adminRegistrationService(adminDTO);
		if (adminDetails != null) {
			return "/AdminPages/AdminLoginPage";
		} else {
			return "/AdminPages/AdminRegistrationPage";
		}
	}

//Admin View All Customer
	@RequestMapping("/adminviewallcustomerpage")
	public String adminViewAllCustomerPage(AdminDTO adminDTO, Model model) {
		List<CustomerEntity> customerDetails = adminService.allPendingCustomerAccoountService("Pending");
		System.out.println(customerDetails);
		System.out.println(adminDTO.getAdminName());
		model.addAttribute("adminDetails", adminDTO);
		model.addAttribute("customerDetails", customerDetails);
		return "/AdminPages/AdminViewAllCustomer";
	}

//Add Customer
	@RequestMapping("/addcustomerpage")
	public String addCustomerPage(@Valid AdminDTO adminDTO, Model model) {
		model.addAttribute("adminDetails", adminDTO);
		return "AdminPages/AddCustomerPage";
	}

	@RequestMapping("customerregistrationadmin")
	public String customerRegistrationAdmin(@Valid CustomerDTO customerDTO, AdminDTO adminDTO, Model model) {
		CustomerEntity customerDetails = adminService.customerRegistrationService(customerDTO);
		String detailsStatus = "";
		if (customerDetails != null) {
			detailsStatus = "Customer Details Are Add";
			model.addAttribute("adminDetails", adminDTO);
			model.addAttribute("customerDetailsStatus", detailsStatus);
			return "AdminPages/AddCustomerPage";
		} else {
			detailsStatus = "Customer Details Not Add";
			model.addAttribute("adminDetails", adminDTO);
			model.addAttribute("customerDetailsStatus", detailsStatus);
			return "AdminPages/AddCustomerPage";
		}
	}

//View Transactions 
	@RequestMapping("/viewtransactionpage")
	public String viewTransactionPage(AdminDTO adminDTO, Model model) {
		model.addAttribute("adminDetails", adminDTO);
		return "AdminPages/AdminViewTransaction";
	}

	@RequestMapping("/viewtransaction")
	public String viewTransaction(AdminDTO adminDTO, String accountNumber, String customerName, Model model) {
		System.out.println(accountNumber + "acc num And name" + customerName);
		 if ((accountNumber == null || accountNumber.trim().isEmpty()) &&
			        (customerName == null || customerName.trim().isEmpty())) {
			String space="";
			model.addAttribute("adminDetails", adminDTO);
			model.addAttribute("customerStatement", null);
		}
		Long accNo=null;
		if (accountNumber != null && !accountNumber.trim().isEmpty()) {
			accNo=Long.parseLong(accountNumber);
			List<CustomerStatementEntity> customerStatement = adminService
					.customerStatementByAccountNumberAndCutomerName(accNo, customerName);
			model.addAttribute("adminDetails", adminDTO);
			model.addAttribute("customerStatement", customerStatement);
		}
		return "AdminPages/AdminViewTransaction";
	}
}
