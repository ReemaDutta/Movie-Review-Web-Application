package com.my.moviereview1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.my.moviereview1.dao.UserDAO;
import com.my.moviereview1.pojo.UserMovies;

@Controller
public class MovieDetailsController {

	private static final Logger logger = LoggerFactory.getLogger(MovieDetailsController.class);
	
	@RequestMapping(value = "/movie", method = RequestMethod.GET)
	public ModelAndView displayDetails(HttpServletRequest request, HttpServletResponse response, UserDAO userDao, ModelMap model) throws IOException {
		ModelAndView mvc = null;

		String key = "3e8655395a80eddd5854026d790f304a";
		String movieId = request.getParameter("id");
		String urlBase = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + key
				+ "&append_to_response=credits&language=en-US";

		URL url;
		HttpURLConnection con;
		BufferedReader br;
		String output;
		url = new URL(urlBase);
		con = (HttpURLConnection) url.openConnection();
		con.setDoOutput(true);
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json");

		br = new BufferedReader(new InputStreamReader((con.getInputStream())));

		while ((output = br.readLine()) != null) {
			System.out.println("+++++++++++++++++++++I am output");
			System.out.println(output);
		}
		
		HttpSession session = request.getSession();
		if(session !=null) {
			session.getAttribute("user");
			session.getAttribute("username");	
			model.addAttribute("user");
			model.addAttribute("username");
			model.addAttribute(session);
		}
			

		JSONObject movie = readJsonFromUrl(urlBase);
		System.out.println("I am jsonobject" + movie.toString());
		mvc = new ModelAndView("movie-details", "movie", movie);
		
		
		//Storing boolean value to session
		
		String username = null;
		username = (String)	session.getAttribute("username");
		if(username != null) {
		boolean flag = userDao.retrieveMovieId(username, movieId);
		//boolean flag = true;
		session.setAttribute("flag", flag);
		}
		
		
		return mvc;
	}



	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		System.out.println("I am StringBuilder" + sb.toString());
		return sb.toString();
	}
	

	public static JSONObject readJsonFromUrl(String url) throws MalformedURLException, IOException {
		InputStream ip = new URL(url).openStream();
		BufferedReader br1 = new BufferedReader(new InputStreamReader(ip, Charset.forName("UTF-8")));
		String JsonString = readAll(br1);
		JSONObject json = new JSONObject(JsonString);
		return json;
	}
	
	
	@RequestMapping(value = "/movie-details", method = RequestMethod.GET)
	public String likeClick(HttpServletRequest request, UserDAO userDao, UserMovies um) throws Exception {	
		
		String key = "3e8655395a80eddd5854026d790f304a";
		String movieId = request.getParameter("id");
		System.out.println(movieId);
		String userName = null;
		HttpSession session = request.getSession();
		if(session !=null) {
			session.getAttribute("user");
			userName = (String) session.getAttribute("username");
			
		}
		if(!userDao.retrieveMovieId(userName, movieId))
			um = userDao.saveUser(userName, movieId);
		else
			userDao.deleteUser(userName, movieId);
		return "redirect:/movie/?id=" + movieId;		
	}
	
	
	
//	public String countLike(HttpServletRequest request, UserDAO userDao, UserMovies um, ModelMap model) {		
//		HttpSession session = request.getSession();
//		String username = null;
//		if(session !=null) {
//		username = (String)	session.getAttribute("username");
//		}
//		String id = request.getParameter("id");		
//		boolean flag = userDao.retrieveMovieId(username, id);
//		model.addAttribute("flag", flag);
//		return "movie-details";
//	}
	
	

}