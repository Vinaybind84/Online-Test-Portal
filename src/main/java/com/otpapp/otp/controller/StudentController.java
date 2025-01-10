package com.otpapp.otp.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.otpapp.otp.dto.ResponseDto;
import com.otpapp.otp.dto.StudentInfoDto;
import com.otpapp.otp.model.Qb;
import com.otpapp.otp.model.Response;
import com.otpapp.otp.model.Result;
import com.otpapp.otp.model.StudentInfo;
import com.otpapp.otp.service.QbRepo;
import com.otpapp.otp.service.ResponseRepo;
import com.otpapp.otp.service.ResultRepo;
import com.otpapp.otp.service.StudentInfoRepo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	StudentInfoRepo srepo;

	@Autowired
	ResponseRepo resrepo;

	@Autowired
	QbRepo qbrepo;
	
	@Autowired
	ResultRepo rrepo;

	@GetMapping("/stdhome")
	public String showStudentHome(HttpSession session, HttpServletResponse responce, Model model) {
		try {
			responce.setHeader("cache-control", "no-cache,no-store,must-revalidate");
			if (session.getAttribute("studentid") != null) {
				StudentInfo sinfo = srepo.findById(session.getAttribute("studentid").toString()).get();
				model.addAttribute("sinfo", sinfo);

				StudentInfoDto dto = new StudentInfoDto();
				model.addAttribute("dto", dto);
				return "/student/studenthome";
			} else {
				return "redirect:/studentlogin";
			}
		} catch (Exception ex) {
			return "redirect:/studentlogin";
		}
	}

	@PostMapping("/stdhome")
	public String UploadPic(HttpSession session, RedirectAttributes redirectAttributes,
			@ModelAttribute StudentInfoDto studentInfoDto) {
		if (session.getAttribute("student") != null) {
			try {
				MultipartFile filedata = studentInfoDto.getProfilepic();
				String storagefilename = new Date().getTime() + "-" + filedata.getOriginalFilename();
				String uploadDir = "public/user";
				Path uploadPath = Paths.get(uploadDir);
				if (!Files.exists(uploadPath))
					;
					
				{
					Files.createDirectories(uploadPath);
					try (InputStream inputStream = filedata.getInputStream()) {
						Files.copy(inputStream, Paths.get(uploadDir + storagefilename),
								StandardCopyOption.REPLACE_EXISTING);
					}
					StudentInfo std = srepo.findById(session.getAttribute("studentid").toString()).get();
					std.setProfilepic(storagefilename);
					srepo.save(std);
					redirectAttributes.addFlashAttribute("msg", "profile uploaded successfully");
					return "redirect:/student/stdhome";
				}
			} catch (Exception ex) {
				redirectAttributes.addFlashAttribute("msg", "something went wrong " + ex.getMessage());
				return "redirect:/student/stdhome";
			}

		} else {
			return "redirect:/studentlogin";
		}
	}

	@GetMapping("/changepassword")
	public String showChangePasswprd(HttpSession session, HttpServletResponse responce) {
		try {
			responce.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
			if (session.getAttribute("studentid") != null) {
				return "student/changepassword";
			} else {
				return "redirect:/student/studentlogin";
			}
		} catch (Exception ex) {
			return "redirect:/studentlogin";
		}
	}

	@PostMapping("/changepassword")
	public String changePassword(HttpSession session, HttpServletResponse response, HttpServletRequest request,
			RedirectAttributes attrib) {
		try {
			response.setHeader("cache-Control", "no-cache,no-store,must-revalidate");
			if (session.getAttribute("studentid") != null) {
				StudentInfo s = srepo.getById(session.getAttribute("studentid").toString());
				String oldpassword = request.getParameter("oldpassword");
				String newpassword = request.getParameter("newpassword");
				String confirmpassword = request.getParameter("confirmpassword");
				if (!newpassword.equals(confirmpassword)) {
					attrib.addFlashAttribute("msg", "New and ConfirmPassword are not match");
					return "redirect:/student/changepassword";
				}
				if (!oldpassword.equals(s.getPassword())) {
					attrib.addFlashAttribute("msg", "old password is not matched");
					return "redirect:/student/changepassword";
				}
				s.setPassword(newpassword);
				srepo.save(s);
				return "redirect:/studentlogin";
			} else {
				return "redirect:/studentlogin";
			}
		} catch (Exception ex) {
			return ":/redirect:/studentlogin";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/studentlogin";
	}
/////////////////////////////////////// res

	@GetMapping("/dashbord")
	public String ShowDashboard(HttpSession session, HttpServletResponse response, Model model) {
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		try {
			if (session.getAttribute("studentid") != null) {
				ResponseDto dto = new ResponseDto();
				model.addAttribute("dto", dto);

				return "student/dashboard";
			} else {
				return "redirect:/studentlogin";
			}
		} catch (Exception ex) {
			return "redirect:/studentlogin";
		}
	}

	@GetMapping("/givetest")
	public String ShowGiveTest(HttpSession session, HttpServletResponse response, Model model) {
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		try {
			if (session.getAttribute("studentid") != null) {
				ResponseDto dto = new ResponseDto();
				model.addAttribute("dto", dto);

				return "student/givetest";
			} else {
				return "redirect:/studentlogin";
			}
		} catch (Exception ex) {
			return "redirect:/studentlogin";
		}
	}

	@GetMapping("/giveresponse")
	public String ShowGiveResponse(HttpSession session, HttpServletResponse response, Model model) {
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		try {
			if (session.getAttribute("studentid") != null) {
				ResponseDto dto = new ResponseDto();
				model.addAttribute("dto", dto);

				return "student/giveresponse";
			} else {
				return "redirect:/studentlogin";
			}
		} catch (Exception ex) {
			return "redirect:/studentlogin";
		}
	}

	
	@PostMapping("/giveresponse")
	public String SubmitResponse(HttpSession session, Model model, @ModelAttribute ResponseDto responseDto,
			RedirectAttributes redirectAttributes, HttpServletResponse responce) {

		try {
			if (session.getAttribute("studentid") != null) {
				StudentInfo std = srepo.getById(session.getAttribute("studentid").toString());
				model.addAttribute("studentid", session.getAttribute("userid"));

				Response res = new Response();

				res.setName(std.getName());
				res.setEnrollmentno(std.getEnrollmentno());
				res.setEmailaddress(std.getEmailaddress());
				res.setContactno(std.getContactno());
				res.setContactno(std.getContactno());
				res.setResponsetype(responseDto.getResponsetype());
				res.setSubject(responseDto.getSubject());
				res.setMessage(responseDto.getMessage());
				res.setResdate(new Date() + "");
				resrepo.save(res);

				
			} else {
				return "redirect:/student/giveresponse";
			}

		} catch (Exception ex) {
			return "redirect:/studentlogin";
		}
		return "redirect:/studentlogin";
	}
	
	@GetMapping("/traningvideo")
	public String ShowTrainingvideos(HttpSession session, HttpServletResponse response, Model model) {
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		try {
			if (session.getAttribute("studentid") != null) {
				ResponseDto dto = new ResponseDto();
				model.addAttribute("dto", dto);

				return "student/traningvideo";
			} else {
				return "redirect:/studentlogin";
			}
		} catch (Exception ex) {
			return "redirect:/studentlogin";
		}
	}
	
	@GetMapping("/starttest")
	public String showStartTest(HttpSession session, HttpServletResponse response, Model model, RedirectAttributes attrib) {
		try {
			response.setHeader("Cache-Control", "no-cache no-store, must-revalidate");
			if (session.getAttribute("studentid") != null) {
				StudentInfo sinfo = srepo.findById(session.getAttribute("studentid").toString()).get();
				model.addAttribute("sinfo", sinfo);	
				String status=rrepo.getStatus(sinfo.getEmailaddress());
				try {
				if(status.equals("true"))
				{
					attrib.addFlashAttribute("msg", "You have already given the test");
					return "redirect:/student/givetest";
				}
				else
				{
					String year=sinfo.getYear();
					List<Qb> qlist=qbrepo.findQbByYear(year);
				   	Gson gson=new Gson();
					String json=gson.toJson(qlist);
					model.addAttribute("json", json);
					model.addAttribute("tt", qlist.size()/2);
					model.addAttribute("tq", qlist.size());
					return "student/starttest";
				}
				}
				catch(Exception ex) {
				String year=sinfo.getYear();
				List<Qb> qlist=qbrepo.findQbByYear(year);
				Gson gson=new Gson();
				String json=gson.toJson(qlist);
				model.addAttribute("json", json);
				model.addAttribute("tt", qlist.size()/2);
				model.addAttribute("tq", qlist.size());
				return "student/starttest";
				}
				
			} else {
				return "redirect:/studentlogin";
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return "redirect:/studentlogin";
		}
		

	}

		
		@GetMapping("/testover")
		public String TestOver(HttpSession session, HttpServletResponse response, @RequestParam int s, @RequestParam int t)
		{
			try {
				response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
				if(session.getAttribute("studentid")!=null)
				{
					StudentInfo si=srepo.getById(session.getAttribute("studentid").toString());
					Result rs=new Result();
					rs.setEmailaddress(si.getEmailaddress());
					rs.setName(si.getName());
					rs.setCollegename(si.getCollegename());
					rs.setCourse(si.getCourse());
					rs.setBranch(si.getBranch());
					rs.setYear(si.getYear());
					rs.setContactno(si.getContactno());
					rs.setTotalmarks(t);
					rs.setGetmarks(s);
					rs.setStatus("true");
					rrepo.save(rs);
					return "student/starttest/testover";
				}
				else
				{
					return "redirect:/studentlogin";
				}
			} catch (Exception e) {
				return "redirect:/studentlogin";
			}
			
		}
	
	
	
	
	

}