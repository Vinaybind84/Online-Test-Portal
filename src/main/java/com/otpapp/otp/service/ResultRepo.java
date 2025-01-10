package com.otpapp.otp.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.otpapp.otp.model.Result;

public interface ResultRepo extends JpaRepository<Result, String> {
	
@Query(value="select status from result where emailaddress=:emailaddress",nativeQuery = true)
	String getStatus( @Param("emailaddress")String emailaddress);

}