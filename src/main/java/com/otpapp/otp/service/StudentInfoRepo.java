package com.otpapp.otp.service;

import org.springframework.data.jpa.repository.JpaRepository;
import com.otpapp.otp.model.StudentInfo;

public interface StudentInfoRepo extends JpaRepository<StudentInfo, String>{

}
