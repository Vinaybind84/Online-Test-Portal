package com.otpapp.otp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table( name="result")
public class Result {
	
	@Id
	@Column(length=50)
	private  String emailaddress;
	@Column(length=50,nullable=false)
	private String name;
	@Column(length=500,nullable=false)
	private String collegename;
	@Column(length=100,nullable=false)
	private String course;
	@Column(length=100,nullable=false)
	private String branch;
	@Column(length= 100,nullable=false)
	private String year;
	@Column(length=15 ,nullable=false)
	private String contactno;
	private int totalmarks;
	private int getmarks;
	
	@Column(length = 10 ,nullable=false)
	private String status;

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCollegename() {
		return collegename;
	}

	public void setCollegename(String collegename) {
		this.collegename = collegename;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getContactno() {
		return contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	public int getTotalmarks() {
		return totalmarks;
	}

	public void setTotalmarks(int totalmarks) {
		this.totalmarks = totalmarks;
	}

	public int getGetmarks() {
		return getmarks;
	}

	public void setGetmarks(int getmarks) {
		this.getmarks = getmarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
