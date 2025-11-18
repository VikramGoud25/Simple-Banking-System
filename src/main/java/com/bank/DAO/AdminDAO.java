package com.bank.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bank.Entity.AdminEntity;
import com.bank.Repo.AdminRepo;

@Repository
public class AdminDAO {
	
	@Autowired
	AdminRepo adminRepo;
	
//Admin Login
	public AdminEntity adminLoginDAO(String adminEmail, String adminPassword) {
		return adminRepo.findByAdminEmailAndAdminPassword(adminEmail, adminPassword);
	}
//Admin Registration
	public AdminEntity adminRegistration(AdminEntity adminEntity) {
		return adminRepo.save(adminEntity);
	}
}
