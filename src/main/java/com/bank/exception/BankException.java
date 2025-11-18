package com.bank.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BankException {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public String bankInvalidExceptionHandler(MethodArgumentNotValidException ex, Model model) {
		Map<String, String> errors = new HashMap<String, String>();
		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
		allErrors.forEach(error -> {
			String field = ((FieldError) error).getField();
			String msg = error.getDefaultMessage();
			errors.put(field, msg);
		});
		model.addAttribute("allFielderrors", errors);
		String objectName = ex.getBindingResult().getObjectName();
		if (objectName.contains("admin")) {
			return "/AdminPages/AdminRegistrationPage";
		} else if (objectName.contains("admin")) {
			return "/AdminPages/AddCustomerPage";
		} else if (objectName.contains("customer")) {
			return "/CustomerPages/CustomerRegistration";
		}

		return "error"; // fallback
	}
}
