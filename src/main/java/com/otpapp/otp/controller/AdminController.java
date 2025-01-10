package com.otpapp.otp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.otpapp.otp.dto.QbDto;
import com.otpapp.otp.model.Enquiry;
import com.otpapp.otp.model.Qb;
import com.otpapp.otp.model.Response;
import com.otpapp.otp.model.Result;
import com.otpapp.otp.model.StudentInfo;
import com.otpapp.otp.service.EnquiryRepo;
import com.otpapp.otp.service.QbRepo;
import com.otpapp.otp.service.ResponseRepo;
import com.otpapp.otp.service.ResultRepo;
import com.otpapp.otp.service.StudentInfoRepo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	StudentInfoRepo stdrepo;

	@Autowired
	ResponseRepo resrepo;

	@Autowired
	EnquiryRepo erepo;

	@Autowired
	QbRepo qbrepo;
	
@Autowired
	ResultRepo rerepo;
	@GetMapping("/adhome")
	public String ShowAdminHome(HttpSession session, HttpServletResponse response, Model model) {
		try {
			response.setHeader("cache-Control", "no-cache.no-store,must-revalidate");
			if (session.getAttribute("adminid") != null) {
long stdcount=stdrepo.count();
model.addAttribute("stdcount",stdcount);

				return "admin/adminhome";
			} else {
				return "redirect:/adminlogin";
			}
		} catch (Exception ex) {
			return "redirect:/adminlogin";

		}
	}

	@GetMapping("/viewstudent")
	public String ShowViewStudent(HttpSession session, HttpServletResponse response, Model model) {
		try {
			response.setHeader("cache-Control", "no-cache.no-store,must-revalidate");
			if (session.getAttribute("adminid") != null) {
				List<StudentInfo> slist = stdrepo.findAll();
				model.addAttribute("slist", slist);

				return "admin/viewstudent";
			} else {
				return "redirect:/adminlogin";
			}
		} catch (Exception ex) {
			return "redirect:/adminlogin";

		}
	}

	@GetMapping("/viewstudent/delete")
	public String DeleteStudent(@RequestParam String email, HttpSession session,RedirectAttributes redirectAttributes){
		try {
			if (session.getAttribute("adminid")!=null){
				StudentInfo st = stdrepo.findById(email).get();
				stdrepo.delete(st);
				redirectAttributes.addFlashAttribute("msg", email+"is deleted successfully");
				return "redirect:/admin/viewstudent";
			} else {
				return "redirect:/adminlogin";

			}
		} catch (Exception ex) {
			return "redirect:/adminlogin";

		}
	}

	@GetMapping("/viewfeedback")
	public String ShowViewFeedback(HttpSession session, HttpServletResponse response, Model model) {
		try {
			response.setHeader("cache-Control", "no-cache.no-store,must-revalidate");
			if (session.getAttribute("adminid") != null) {

				List<Response> flist = resrepo.FindResponseByResponseType("feedback");
				model.addAttribute("flist", flist);
				return "admin/viewfeedback";

			} else {
				return "redirect:/adminlogin";
			}
		} catch (Exception ex) {
			return "redirect:/adminlogin";

		}
	}
	
	@GetMapping("/viewfeedback/delete")
	public String DeleteFeedback(@RequestParam int resid, HttpSession session,RedirectAttributes redirectAttributes){
		try {
			if (session.getAttribute("adminid")!=null){
				Response res = resrepo.findById(resid).get();
				resrepo.delete(res);
				redirectAttributes.addFlashAttribute("msg",resid +" feedback is deleted successfully");
				return "redirect:/admin/viewfeedback";
			} else {
				return "redirect:/adminlogin";

			}
		} catch (Exception ex) {
			return "redirect:/adminlogin";

		}
	}

	@GetMapping("/viewcomplain")
	public String ShowViewComplaint(HttpSession session, HttpServletResponse response, Model model) {
		try {
			response.setHeader("cache-Control", "no-cache.no-store,must-revalidate");
			if (session.getAttribute("adminid") != null) {

				List<Response> clist = resrepo.FindResponseByResponseType("complain");
				model.addAttribute("clist", clist);
				return "admin/viewcomplain";

			} else {
				return "redirect:/adminlogin";
			}
		} catch (Exception ex) {
			return "redirect:/adminlogin";

		}
	}
	
	@GetMapping("/viewcomplain/delete")
	public String DeleteComplain(@RequestParam int resid, HttpSession session,RedirectAttributes redirectAttributes){
		try {
			if (session.getAttribute("adminid")!=null){
				Response res = resrepo.findById(resid).get();
				resrepo.delete(res);
				redirectAttributes.addFlashAttribute("msg",resid +" feedback is deleted successfully");
				return "redirect:/admin/viewcomplain";
			} else {
				return "redirect:/adminlogin";

			}
		} catch (Exception ex) {
			return "redirect:/adminlogin";

		}
	}


	@GetMapping("/viewenquiry")
	public String ShowViewEnquiries(HttpSession session, HttpServletResponse response, Model model) {
		try {
			response.setHeader("cache-Control", "no-cache.no-store,must-revalidate");
			if (session.getAttribute("adminid") != null) {
				List<Enquiry> elist = erepo.findAll();
				model.addAttribute("elist", elist);

				return "admin/viewenquiry";
			} else {
				return "redirect:/adminlogin";
			}
		} catch (Exception ex) {
			return "redirect:/adminlogin";

		}
	}

	@GetMapping("/changepassword")
	public String showChangePasswprd(HttpSession session, HttpServletResponse responce) {
		try {
			responce.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
			if (session.getAttribute("adminid") != null) {
				return "admin/changepassword";
			} else {
				return "redirect:/admin/adminlogin";
			}
		} catch (Exception ex) {
			return "redirect:/adminlogin";
		}
	}

	@PostMapping("/changepassword")
	public String changePassword(HttpSession session, HttpServletResponse response, HttpServletRequest request,
			RedirectAttributes attrib) {
		try {
			response.setHeader("cache-Control", "no-cache,no-store,must-revalidate");
			if (session.getAttribute("adminid") != null) {
				StudentInfo s = stdrepo.getById(session.getAttribute("adminid").toString());
				String oldpassword = request.getParameter("oldpassword");
				String newpassword = request.getParameter("newpassword");
				String confirmpassword = request.getParameter("confirmpassword");
				if (!newpassword.equals(confirmpassword)) {
					attrib.addFlashAttribute("msg", "New and ConfirmPassword are not match");
					return "redirect:/admin/changepassword";
				}
				if (!oldpassword.equals(s.getPassword())) {
					attrib.addFlashAttribute("msg", "old password is not matched");
					return "redirect:/admin/changepassword";
				}
				s.setPassword(newpassword);
				stdrepo.save(s);
				return "redirect:/adminlogin";
			} else {
				return "redirect:/adminlogin";
			}
		} catch (Exception ex) {
			return ":/redirect:/adminlogin";
		}
	}

	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/adminlogin";
	}

	@GetMapping("/addqb")
	public String AddQb(HttpSession session, HttpServletResponse response, Model model) {
		try {
			response.setHeader("cache-Control", "no-cache.no-store,must-revalidate");
			if (session.getAttribute("adminid") != null) {
				QbDto dto = new QbDto();
				model.addAttribute("dto", dto);
				return "admin/addqb";
			} else {
				return "redirect:/adminlogin";
			}
		} catch (Exception ex) {
			return "redirect:/adminlogin";

		}
	}

	@PostMapping("/addqb")
	public String CreateQb(HttpSession session, HttpServletResponse response, @ModelAttribute QbDto dto,
			RedirectAttributes attrib) {
		try {
			response.setHeader("cache-Control", "no-cache.no-store,must-revalidate");
			if (session.getAttribute("adminid") != null) {
				Qb qb = new Qb();
				qb.setYear(dto.getYear());
				qb.setQuestion(dto.getQuestion());
				qb.setA(dto.getA());
				qb.setB(dto.getB());
				qb.setC(dto.getC());
				qb.setD(dto.getD());
				qb.setCorrect(dto.getCorrect());
				qbrepo.save(qb);
				attrib.addFlashAttribute("msg", "Question is added");
				return "redirect:/admin/addqb";
			} else {
				return "redirect:/adminlogin";
			}
		} catch (Exception ex) {
			return "redirect:/adminlogin";

		}
	}

	@GetMapping("/viewqb")
	public String ViewQB(HttpSession session, Model model, HttpServletResponse response) {
		try {
			if (session.getAttribute("adminid") != null) {
				List<Qb> qblist = qbrepo.findAll();
				model.addAttribute("qblist", qblist);
				return "admin/viewqb";
			} else {
				return "redirect:/adminlogin";
			}
		} catch (Exception ex) {
			return "redirect:/adminlogin";
		}
	}
	
	

	@GetMapping("/viewqb/delete")
	public String DeleteQB( @RequestParam int id,RedirectAttributes redirectAttributes, HttpSession session)
	{
		try {
			if(session.getAttribute("adminid")!=null){
				Qb qb=qbrepo.findById(id).get();
				qbrepo.delete(qb);
				redirectAttributes.addFlashAttribute("msg",id+"question delete succesfully");
				return"redirect:/admin/viewqb"	;		
			}else {
				return "redirect:/adminlogin";
			}
		}catch(Exception ex) {
			return "redirect:/adminlogin";
		}		
}
	@GetMapping("/manageresult")
	public String ManageResult(HttpSession session, Model model, HttpServletResponse response) {
		try {
			if (session.getAttribute("adminid") != null) {
				List<Result> rlist= rerepo.findAll();
				model.addAttribute("rlist", rlist);
				return "admin/manageresult";
			} else {
				return "redirect:/adminlogin";
			}
		} catch (Exception ex) {
			return "redirect:/adminlogin";
		}
	}	
	
}