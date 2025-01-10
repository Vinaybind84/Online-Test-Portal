package com.otpapp.otp.model;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "studentinfoes")
public class StudentInfo {
	
	@Column(length = 100,nullable = false)
	private String enrollmentno;
	
	@Column(length = 50,nullable = false)
	private String name;
	
	@Column(length = 13,nullable = false)
	private String contactno;
	
	@Column(length = 13,nullable = false)
	private String whatsappno;
	@Id
	@Column(length = 100)
	private String emailaddress;
	
	@Column(length = 30,nullable = false)
	private String password;
	
	@Column(length = 500, nullable = false)
	private String collegename;
	
	@Column(length = 100, nullable = false)
	private String course;
	
	@Column(length = 100,nullable = false)
	private String branch;
	
	@Column(length = 50,nullable = false)
	private String year;
	
	@Column(length = 50,nullable = false)
	private String highschool;
	
	@Column(length = 50,nullable = false)
	private String intermediate;
	
	@Column(length = 50,nullable = false)
	private String aggregatemarks;
	
	@Column(length = 15,nullable = false)
	private String traningmode;
	
	@Column(length = 60,nullable = false)
	private String traninglocation;
	
	@Column(length = 60,nullable = false)
	private String regdate;
	
	@Column(length = 500,nullable=true)
	private String profilepic;

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

	public String getProfilepic() {
		return profilepic;
	}

	public void setProfilepic(String profilepic) {
		this.profilepic = profilepic;
	}

	
}
	