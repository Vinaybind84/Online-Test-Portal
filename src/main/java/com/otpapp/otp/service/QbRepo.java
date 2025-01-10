package com.otpapp.otp.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.otpapp.otp.model.Qb;

public interface QbRepo  extends JpaRepository<Qb, Integer>{

	List<Qb> findQbByYear(String year);

}
