package com.bank.contoller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bank.DTO.CustomerDTO;
import com.bank.DTO.CustomerStatementDTO;
import com.bank.Entity.CustomerEntity;
import com.bank.service.CustomerService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/customer")
public class CustomerContoller {

	@Autowired
	CustomerService customerService;

//Customer Login
	@RequestMapping("customerloginpage")
	public String customerLoginPage() {
		return "/CustomerPages/CustomerLogin";
	}

	@RequestMapping("customerlogin")
	public String customerLogin(CustomerDTO customerDTO, Model model) {
		CustomerEntity customerDetails = customerService.customerLoginService(customerDTO);
		if (customerDetails != null) {
			model.addAttribute("customerDetails", customerDetails);
			return "/BankPages/BankHome";
		} else {
			return "/CustomerPages/CustomerLogin";
		}
	}

//Customer Registration 

	@RequestMapping("/customerregistrationpage")
	public String customerRegistrationPage(CustomerDTO customerDTO) {
		return "/CustomerPages/CustomerRegistration";
	}

	@RequestMapping("customerregistration")
	public String customerRegistration(@Valid CustomerDTO customerDTO) {
		CustomerEntity customerDetails = customerService.customerRegistrationService(customerDTO);
		if (customerDetails != null) {
			return "/CustomerPages/CustomerLogin";
		} else {
			return "/CustomerPages/CustomerRegistration";
		}
	}

//Customer Deposit

	@RequestMapping("/customerdepositpage")
	public String customerDepositPage(CustomerDTO customerDTO, Model model) {
		model.addAttribute("customerDetails", customerDTO);
		return "CustomerPages/CustomerDeposit";
	}

	@RequestMapping("/customerdeposit")
	public String customerDeposit(CustomerDTO customerDTO, long accountNumber, long depositAmount, Model model) {
		String depositStatus = "Account Number Is Wrong";
		if (customerDTO.getCustomerAccountNumber() == accountNumber) {
			if (customerService.checkCustomerNameAndCustomerAccountNumberService(customerDTO.getCustomerName(),
					accountNumber) != null) {
				long updateAmount = customerDTO.getCustomerAmount() + depositAmount;
				customerDTO.setCustomerAmount(updateAmount);
				int customerDetails = customerService.updateCustomerAmountBYCustomerAccountNumberServiec(
						customerDTO.getCustomerAccountNumber(), customerDTO.getCustomerAmount());
				CustomerEntity updateCustomerDetails = customerService
						.checkCustomerNameAndCustomerAccountNumberService(customerDTO.getCustomerName(), accountNumber);

				CustomerStatementDTO customerStatementDTO = new CustomerStatementDTO();
				LocalDate currentDate = LocalDate.now();

				customerStatementDTO.setCustomerId(updateCustomerDetails.getCustomerId());
				customerStatementDTO
						.setCustomerStatementAccountNumber(updateCustomerDetails.getCustomerAccountNumber());
				customerStatementDTO.setCustomerStatementName(updateCustomerDetails.getCustomerName());
				customerStatementDTO.setCustomerStatementType("Deposit");
				customerStatementDTO.setCustomerTotalAmount(customerDTO.getCustomerAmount());
				customerStatementDTO.setCustomerAmount(depositAmount);
				customerStatementDTO.setCustomerStatementDate(currentDate);
				customerStatementDTO.setCustomerStatementStaus("Successful");
				customerService.insertCustomerStatementSerivec(customerStatementDTO);
				depositStatus = customerDetails != 0 ? "Your " + customerDTO.getCustomerAmount() + " Amount Deposit"
						: "Your " + customerDTO.getCustomerAmount() + " Amount Not Deposit";
			}
		}
		model.addAttribute("customerDetails", customerDTO);
		model.addAttribute("depositStatus", depositStatus);
		return "CustomerPages/CustomerDeposit";
	}

//Customer Withdraw

	@RequestMapping("/customerwithdrawpage")
	public String customerWithdrawPage(CustomerDTO customerDTO, Model model) {
		model.addAttribute("customerDetails", customerDTO);
		return "CustomerPages/CustomerWithdraw";
	}
	
	@RequestMapping("/customerwithdraw")
	public String customerWithdraw(CustomerDTO customerDTO, long accountNumber, long withdrawAmount, Model model) {
		System.out.println(accountNumber+"acc num and withdraw amount"+withdrawAmount);
		String withdrawStatus = "Account Number Is Wrong";
		if (customerDTO.getCustomerAccountNumber() == accountNumber) {
			if (customerService.checkCustomerNameAndCustomerAccountNumberService(customerDTO.getCustomerName(),
					accountNumber) != null) {
				long updateAmount = customerDTO.getCustomerAmount() - withdrawAmount;
				customerDTO.setCustomerAmount(updateAmount);
				int customerDetails = customerService.updateCustomerAmountBYCustomerAccountNumberServiec(
						customerDTO.getCustomerAccountNumber(), customerDTO.getCustomerAmount());
				CustomerEntity updateCustomerDetails = customerService
						.checkCustomerNameAndCustomerAccountNumberService(customerDTO.getCustomerName(), accountNumber);

				CustomerStatementDTO customerStatementDTO = new CustomerStatementDTO();
				LocalDate currentDate = LocalDate.now();

				customerStatementDTO.setCustomerId(updateCustomerDetails.getCustomerId());
				customerStatementDTO
						.setCustomerStatementAccountNumber(updateCustomerDetails.getCustomerAccountNumber());
				customerStatementDTO.setCustomerStatementName(updateCustomerDetails.getCustomerName());
				customerStatementDTO.setCustomerStatementType("Withdraw");
				customerStatementDTO.setCustomerTotalAmount(customerDTO.getCustomerAmount());
				customerStatementDTO.setCustomerAmount(withdrawAmount);
				customerStatementDTO.setCustomerStatementDate(currentDate);
				customerStatementDTO.setCustomerStatementStaus("Successful");
				customerService.insertCustomerStatementSerivec(customerStatementDTO);
				withdrawStatus = customerDetails != 0 ? "Your " + customerDTO.getCustomerAmount() + " Amount Withdraw"
						: "Your " + customerDTO.getCustomerAmount() + " Amount Not Withdraw";
			}
		}
		model.addAttribute("customerDetails", customerDTO);
		model.addAttribute("withdrawStatus", withdrawStatus);
		return "CustomerPages/CustomerWithdraw";
	}
	
//Customer Check Balance
	
	@RequestMapping("/customercheckbalancepage")
	public String customerCheckBalancePage(CustomerDTO customerDTO, Model model) {
		model.addAttribute("customerDetails", customerDTO);
		return "CustomerPages/CustomerCheckBalance";
	}
	
	@RequestMapping("/customercheckbalance")
	public String customerCheckBalance(CustomerDTO customerDTO, long accountNumber, Model model) {
		String checkBalanceStatus = "Account Number Is Wrong";
		if (customerDTO.getCustomerAccountNumber() == accountNumber) {
			CustomerEntity customerDetails=customerService.checkCustomerNameAndCustomerAccountNumberService(customerDTO.getCustomerName(),
					accountNumber);
			if ( customerDetails!= null) {
				checkBalanceStatus="available balance : "+customerDetails.getCustomerAmount();
			}
		}
		model.addAttribute("customerDetails", customerDTO);
		model.addAttribute("checkBalanceStatus", checkBalanceStatus);
		return "CustomerPages/CustomerCheckBalance";
	}

}
