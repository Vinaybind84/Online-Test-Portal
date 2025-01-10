package com.otpapp.otp.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "enquiries")
public class Enquiry {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

@Column(length = 50, nullable=false)
private String name;

@Column(length = 6, nullable=false)
private String gender;

@Column(length = 15, nullable=false)
private String contactno;

@Column(length = 50, nullable=false)
private String emailadress;

@Column(length = 50, nullable=false)
private String enquirytext;

@Column(length = 50, nullable=false)
private String posteddate;

public int getID() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender=gender;
}
public String getContactno() {
	return contactno;
}
public void setContactno(String contactno) {
	this.contactno = contactno;
}
public String getEmailaddress() {
	return emailadress;
}
public void setEmailaddress(String emailaddress) {
	this.emailadress = emailaddress;
}
public String getEnquirytext() {
	return enquirytext;
}
public void setEnquirytext(String enquirytext) {
	this.enquirytext = enquirytext;
}
public String getPosteddate() {
	return posteddate;
}
public void setPosteddate(String posteddate) {
	this.posteddate = posteddate;
}

}




