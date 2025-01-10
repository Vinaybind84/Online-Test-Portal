package com.otpapp.otp.dto;

import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.web.multipart.MultipartFile;

public class StudentInfoDto {
	private String enrollmentno;
	private String name;
	private String contactno;
	private String whatsappno;
	private String emailaddress;
	private String password;
	private String collegename;
	private String course;
	private String branch;
	private String year;
	private String highschool;
	private String intermediate;
	private String aggregatemarks;
	private String traningmode;
	private String traninglocation;
	private String regdate;
	private MultipartFile profilepic;
	public String getEnrollmentno() {
		return enrollmentno;
	}
	public void setEnrollmentno(String enrollmentno) {
		this.enrollmentno = enrollmentno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactno() {
		return contactno;
	}
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}
	public String getWhatsappno() {
		return whatsappno;
	}
	public void setWhatsappno(String whatsappno) {
		this.whatsappno = whatsappno;
	}
	public String getEmailaddress() {
		return emailaddress;
	}
	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getHighschool() {
		return highschool;
	}
	public void setHighschool(String highschool) {
		this.highschool = highschool;
	}
	public String getIntermediate() {
		return intermediate;
	}
	public void setIntermediate(String intermediate) {
		this.intermediate = intermediate;
	}
	public String getAggregatemarks() {
		return aggregatemarks;
	}
	public void setAggregatemarks(String aggregatemarks) {
		this.aggregatemarks = aggregatemarks;
	}
	public String getTraningmode() {
		return traningmode;
	}
	public void setTraningmode(String traningmode) {
		this.traningmode = traningmode;
	}
	public String getTraninglocation() {
		return traninglocation;
	}
	public void setTraninglocation(String traninglocation) {
		this.traninglocation = traninglocation;
		
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public MultipartFile getProfilepic() {
		return profilepic;
	}
	public void setProfilepic(MultipartFile profilepic) {
		this.profilepic = profilepic;
	}
	
	
	
	
}
	
	
	
	