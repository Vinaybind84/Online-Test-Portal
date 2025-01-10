package com.otpapp.otp.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.otpapp.otp.model.AdminInfo;

public interface AdminInfoRepo extends JpaRepository<AdminInfo, String>{

}
