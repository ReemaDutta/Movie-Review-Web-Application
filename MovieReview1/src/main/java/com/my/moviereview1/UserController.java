package com.my.moviereview1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.my.moviereview1.exception.UserException;
import com.my.moviereview1.dao.UserDAO;
import com.my.moviereview1.pojo.User;
import com.my.moviereview1.pojo.UserMovies;
import com.my.moviereview1.validator.UserValidator;

@Controller
public class UserController {

	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response,ModelMap model, @ModelAttribute("user") User user) throws IOException {

		String value = request.getParameter("type");		
		//String action = request.getParameter("action");
		ModelAndView mvc = null;		
		if (value.equals("Login")) {
			mvc = new ModelAndView("user-form");
		} else if (value.equals("Register")) {
			mvc = new ModelAndView("register-user", "user", new User());
		} else if (value.equals("Logout")) {
			HttpSession session = request.getSession();
			if (session != null) {
				session.invalidate();
				response.sendRedirect("/moviereview1/");
			}
			
		} else if (value.equals("User Profile")) {
			HttpSession session = request.getSession(false);
			String username = (String) session.getAttribute("username");
			if (session != null) {
				if (session.getAttribute("username") != null) {
				//	List<UserMovies> list = userDao.retrieveMovieUser(username);
				//	model.addAttribute("list", list);
					//mvc = new ModelAndView("user-profile", "list", list);
					response.sendRedirect("/moviereview1/user-profile");
				}
			}
		
		} else if (value.equals("admin")) {
			HttpSession session = request.getSession(false);
			if (session != null) {
				String userid = (String) session.getAttribute("userid");
				if (userid.equals("admin")) {					 
					 session.setAttribute("userid", userid);
					 response.sendRedirect("/moviereview1/movie-admin");
				}
			}
		}else if (value.equals("MyMovies")) {
			HttpSession session = request.getSession(false);
			String username = (String) session.getAttribute("username");
			if (session != null) {
				if (session.getAttribute("username") != null) {
					response.sendRedirect("/moviereview1/user-movies");
					//mvc = new ModelAndView("user-movies");
				}
			}
		}else if(value.equals("search")) {
			String searchQuery = request.getParameter("query");
			System.out.println(searchQuery);
			model.addAttribute("searchQuery", searchQuery);
			mvc = new ModelAndView("redirect:/","searchQuery",searchQuery);
			
		}else if(value.equals("Home")) {
			HttpSession session = request.getSession(false);
			if(session!=null) {
			mvc = new ModelAndView("redirect:/");
			}
		}
		return mvc;
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.GET)
	protected ModelAndView userForm() throws Exception {
		System.out.print("User Logged In");
		return new ModelAndView("user-form");
	}
	
	
	
	
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	protected String userLogin(HttpServletRequest request, ModelMap model) throws Exception {
		System.out.print("User Logged In");
		HttpSession session = (HttpSession) request.getSession();
		String userid = request.getParameter("username");
		String password = request.getParameter("password");
		//String cancel = request.getParameter("Cancel");
		User u = null;
			try {
				System.out.print("loginUser");
				u = userDao.get(userid,password);			
				if(u == null )  {
					System.out.println("UserName/Password does not exist");
					model.addAttribute("errorMessage", "UserName/Password does not exist");
					return "error";
				} else if (!(u.getUsername().equals("userid") || (u.getPassword().equals(password))))
					{
						System.out.println("UserName/Password does not exist");
						model.addAttribute("errorMessage", "UserName/Password does not exist");
						return "error";
					}
				session.setAttribute("user", u);
				session.setAttribute("username", u.getFirstName());		
				session.setAttribute("userid", u.getUsername());
				//return "redirect:/./movies";

			} catch (UserException e) {
				System.out.println("Exception: " + e.getMessage());
				session.setAttribute("errorMessage", "error while login");
				return "error";
			}			
			
			//ModelAndView mvc = new ModelAndView("movies", "movieArray", model.get("movieArray"));
			return "redirect:/";
	}

	@RequestMapping(value = "/user/register", method = RequestMethod.GET)
	protected ModelAndView registerUser() throws Exception {
		System.out.print("Register New User");
		return new ModelAndView("register-user", "user", new User());
	}

	@RequestMapping(value = "/user/register", method = RequestMethod.POST)	
	protected String registerNewUser(HttpServletRequest request, @ModelAttribute("user") User user,
			BindingResult result) throws Exception {
		System.out.print("Register New User");
		
		validator.validate(user, result);

		if (result.hasErrors()) {
			return "error";
		}
		
			
			ArrayList<User> userlist= (ArrayList<User>) userDao.retrieveUser();
			String usernam= user.getUsername();
			for(User u: userlist) {
				if(usernam.equals(u.getUsername())) {					
					request.getSession().setAttribute("errorMessage", "User Name already exist!! Please try again with your existing id");
					return "error";
				}
			}
			
			if((user.getUsername().length()) < 8 || (user.getUsername().length() > 30) || 
					(user.getPassword().length()) <8 || (user.getPassword().length()) > 30) {
				request.getSession().setAttribute("errorMessage", "User Name and Password length must be in between 8 and 30" );
				return "error";
			}
			
			HttpSession session = request.getSession(false);
			System.out.print("registerNewUser");
			User u = userDao.register(user);
			request.getSession().setAttribute("user", u);
			session.setAttribute("user", u);
			session.setAttribute("username", u.getFirstName());		
			session.setAttribute("userid", u.getUsername());
			return "redirect:/";	
		
	}

}
