package com.my.moviereview1;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.my.moviereview1.dao.UserDAO;
import com.my.moviereview1.exception.UserException;
import com.my.moviereview1.pojo.User;
import com.my.moviereview1.report.ExcelView;
import com.my.moviereview1.report.PdfView;

@Controller
public class UserAdminController {
	
	
	
	@RequestMapping(value = "/movie-admin", method = RequestMethod.GET)
	public ModelAndView displayUser(HttpServletRequest request, HttpServletResponse response,UserDAO userDao, ModelMap model) throws UserException {
		
		HttpSession session = request.getSession(false);
		ArrayList<User> userlist = new ArrayList<User>();
		if(session.getAttribute("userid").equals("admin")) {
			userlist = userDao.displayUser((String)session.getAttribute("userid"));					
		}
		return new ModelAndView("movie-admin", "userlist", userlist);
	}
	
	
	@RequestMapping(value = "/user/delete.htm", method = RequestMethod.GET)
	public ModelAndView deleteUser(HttpServletRequest request, UserDAO userDao) throws Exception {
		
		ModelAndView mv = null;			
		String id = request.getParameter("username");
		userDao.deleteUserByAdmin(id);	
		mv = new ModelAndView("user-deleted");	
		return mv;	
	}
	
	
	@RequestMapping(value = "/movie-admin", method = RequestMethod.POST)
	protected ModelAndView saveCSV(HttpServletRequest request, HttpServletResponse response, UserDAO userDao) {
		
		ModelAndView mv = null;	
		String name = request.getParameter("action");
		ArrayList<User> list = (ArrayList<User>) userDao.retrieveUser();
		if(name.equals("Excel")) {												
			mv = new ModelAndView(new ExcelView(), "list", list);	
		}else if(name.equals("PDF")) {						
			mv = new ModelAndView(new PdfView(), "list", list);	
		}
		return mv;
	}
	
	
//	@RequestMapping(value = "/movie-admin/pdf",method = RequestMethod.POST)
//	protected ModelAndView savePDF(HttpServletRequest request, HttpServletResponse response, UserDAO userDao) {
//
//		ModelAndView mv = null;				
//			
//		return mv;
//	}

}
