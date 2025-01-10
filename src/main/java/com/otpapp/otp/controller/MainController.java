package com.otpapp.otp.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.otpapp.otp.api.SmsSender;
import com.otpapp.otp.dto.AdminInfoDto;
import com.otpapp.otp.dto.EnquiryDto;
import com.otpapp.otp.dto.StudentInfoDto;
import com.otpapp.otp.model.AdminInfo;
import com.otpapp.otp.model.Enquiry;
import com.otpapp.otp.model.StudentInfo;
import com.otpapp.otp.service.AdminInfoRepo;
import com.otpapp.otp.service.EnquiryRepo;
import com.otpapp.otp.service.StudentInfoRepo;

import jakarta.persistence.EntityNotFoundException;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

	@Autowired
	EnquiryRepo erepo;

	@Autowired
	StudentInfoRepo stdrepo;
	
	@Autowired
	AdminInfoRepo adrepo;

	@GetMapping("/index")
	public String ShowIndex() {
		return "index";
	}

	// ------------------------------------------------------------------------------------------------

	@GetMapping("/registration")
	 public String ShowRegistration(Model model)
	 {
		 StudentInfoDto dto= new StudentInfoDto();
		 model.addAttribute("dto",dto);
		 return"registration";
	 }
	 @PostMapping("/registration")
	 public String Registartion(@ModelAttribute StudentInfoDto studentInfoDto , RedirectAttributes redirectattributes )
	 {
	try {
		StudentInfo std=new StudentInfo();
		std.setEnrollmentno(studentInfoDto.getEnrollmentno());
		std.setName(studentInfoDto.getName());
		std.setContactno(studentInfoDto.getContactno());
		std.setWhatsappno(studentInfoDto.getWhatsappno());
		std.setEmailaddress(studentInfoDto.getEmailaddress());
		std.setPassword(studentInfoDto.getPassword());
		std.setCollegename(studentInfoDto.getCollegename());
		std.setCourse(studentInfoDto.getCourse());
		std.setBranch(studentInfoDto.getBranch());
		std.setYear(studentInfoDto.getYear());
		std.setHighschool(studentInfoDto.getHighschool());
		std.setIntermediate(studentInfoDto.getIntermediate());
		std.setAggregatemarks(studentInfoDto.getAggregatemarks());
		std.setTraningmode(studentInfoDto.getTraningmode());
		std.setTraninglocation(studentInfoDto.getTraninglocation());
		std.setRegdate(new Date()+"");
		stdrepo.save(std);
	    redirectattributes.addFlashAttribute("message","registration successfully");
		return"redirect:/registration";
	}
	catch(Exception e) {

	    redirectattributes.addFlashAttribute("message","registration unsuccessfully"+e.getMessage());
		return"redirect:/registration";
	}
	
		
	 }
	
	///////////////////about us
	
	@GetMapping("/aboutus")
	public String ShowAboutus()
	{
		return "aboutus";
	}

	// --------------------------------------------------------------------------------------------------------
	@GetMapping("/contactus")

	public String showcontactus(Model model) {
		Enquiry dto = new Enquiry();
		model.addAttribute("dto", dto);
		return "contactus";
	}

	@PostMapping("/contactus")

	public String SubmitEnquiry(@ModelAttribute Enquiry enquiryDto, BindingResult result,
			RedirectAttributes redirectAttributes) {
		try {

			Enquiry eq = new Enquiry();
			eq.setName(enquiryDto.getName());
			eq.setGender(enquiryDto.getGender());
			eq.setContactno(enquiryDto.getContactno());
			eq.setEmailaddress(enquiryDto.getEmailaddress());
			eq.setEnquirytext(enquiryDto.getEnquirytext());
			eq.setPosteddate(new Date() + "");
			erepo.save(eq);
			SmsSender ss=new SmsSender();
			ss.sendSms(enquiryDto.getContactno());
			redirectAttributes.addFlashAttribute("message", "Form Submitted Successfully");
			return "redirect:/contactus";
		} catch (Exception ex) {
			redirectAttributes.addFlashAttribute("message", "Something went wrong");
			return "redirect:/contactus";
		}

	}

	@GetMapping("/studentlogin")
	public String showstudentLogin(Model model){
		StudentInfoDto dto = new StudentInfoDto();
		model.addAttribute("dto",dto);
		return "studentlogin";
	}

	@PostMapping("/studentlogin")
	public String validateStudent(@ModelAttribute StudentInfoDto dto,HttpSession session,RedirectAttributes attrib)
	{
	try {
		StudentInfo s=stdrepo.getById(dto.getEmailaddress());
				if(s.getPassword().equals(dto.getPassword())) {
					//attrib.addFlashAttribute("msg","Valid user");
					session.setAttribute("studentid", s.getEmailaddress());
					return"redirect:/student/stdhome";	
				}
				else {
					attrib.addFlashAttribute("msg","Invalid user");
					return"redirect:/studentlogin";
				}
	}	
	catch(EntityNotFoundException ex){
		attrib.addFlashAttribute("msg","Student doesn't exits");
		return "redirect:/studentlogin";
	}
	}
	
	
	@GetMapping("/adminlogin")
	public String showAdminLogin(Model model)
	{
		AdminInfoDto dto= new AdminInfoDto();
		model.addAttribute("dto",dto);
		return "adminlogin";
	}
	@PostMapping("/adminlogin")
	public String AdminLogin(@ModelAttribute AdminInfoDto adminInfoDto,HttpSession session,RedirectAttributes redirectAttributes)
	{
		try {
			AdminInfo adinfo=adrepo.getById(adminInfoDto.getUserid());
			if(adinfo.getPassword().equals(adminInfoDto.getPassword()))
			{
			//redirectAttributes.addFlashAttribute("msg","Valid User");
				session.setAttribute("adminid", adminInfoDto.getUserid());
			return "redirect:/admin/adhome";

			}
			else {
				redirectAttributes.addFlashAttribute("msg","invalid Users");
				return "redirect:/adminlogin";

			}
		}
		catch (Exception ex) 
		{
			redirectAttributes.addFlashAttribute("msg","User does not exits" +ex.getMessage());
			return "redirect:/adminlogin";
		}
		
	}
}