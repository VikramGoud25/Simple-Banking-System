package com.bank.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.Entity.AdminEntity;

public interface AdminRepo extends JpaRepository<AdminEntity, Integer>{
	AdminEntity findByAdminEmailAndAdminPassword(String adminEmail, String adminPassword);
}
