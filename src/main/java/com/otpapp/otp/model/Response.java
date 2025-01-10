package com.otpapp.otp.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "response")
public class Response {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int resid;
	
	@Column(length = 60, nullable = false)
	private String name;
	
	@Column(length = 50, nullable = false)
	private String enrollmentno;
	
	@Column(length = 100, nullable = false)
	private String emailaddress;
	
	@Column(length = 13, nullable = false)
	private String contactno;
	
	@Column(length = 50, nullable = false)
	private String responsetype;
	
	@Column(length = 200, nullable = false)
	private String subject;
	
	@Column(length =  1000,nullable = false)
	private String message; 
	
	@Column(length =  50,nullable = false)
	private String resdate;

	public int getResid() {
		return resid;
	}

	public void setResid(int resid) {
		this.resid = resid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnrollmentno() {
		return enrollmentno;
	}

	public void setEnrollmentno(String enrollmentno) {
		this.enrollmentno = enrollmentno;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getContactno() {
		return contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	public String getResponsetype() {
		return responsetype;
	}

	public void setResponsetype(String responsetype) {
		this.responsetype = responsetype;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResdate() {
		return resdate;
	}

	public void setResdate(String resdate) {
		this.resdate = resdate;
	} 
	
	
}
