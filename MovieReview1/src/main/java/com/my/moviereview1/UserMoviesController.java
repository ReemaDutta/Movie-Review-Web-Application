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
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
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
import org.springframework.web.servlet.ModelAndView;

import com.my.moviereview1.dao.UserDAO;
import com.my.moviereview1.pojo.User;
import com.my.moviereview1.pojo.UserMovies;
import com.my.moviereview1.validator.UserValidator;

@Controller
public class UserMoviesController {
	

	@RequestMapping(value = "/user-movies", method = RequestMethod.GET)
	public ModelAndView displayDetails(HttpServletRequest request, HttpServletResponse response, UserDAO userDao,
			ModelMap model) throws IOException {
		ModelAndView mvc = null;

		String key = "3e8655395a80eddd5854026d790f304a";
		JSONArray ja = new JSONArray();
		HttpSession session = request.getSession(false);
		String username = null;
		ArrayList movies = new ArrayList();
		List<UserMovies> list = new ArrayList<UserMovies>();
		
		if (session != null) {
			username = (String) session.getAttribute("username");
			session.getAttribute("user");
			if (username != null) {
				list = userDao.retrieveMovieUser(username);
			}

			if (list.size() <= 0) {
				mvc = new ModelAndView("user-movies", "message", "Currently No Movies Added!!");
			}

			for (UserMovies um : list) {
				String movieid = um.getMovieid();
				
				movies.add(movieid);
				String urlBase = "https://api.themoviedb.org/3/movie/" + movieid + "?api_key=" + key
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

				JSONObject movie = readJsonFromUrl(urlBase);
				System.out.println("I am jsonobject" + movie.toString());

				ja.put(movie);
				// System.out.println("I am inside list" +movieid);

			}

			for (int i = 0; i < ja.length(); i++) {
				// System.out.println("Json length" +movieArray.length());
				JSONObject obj = ja.getJSONObject(i);
				System.out.println("Movie Title	" + obj.get("title"));
			}

			if (list.size() > 0) {
				mvc = new ModelAndView("user-movies", "ja", ja);
			}

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
	
	
	@RequestMapping(value = "/user-profile", method = RequestMethod.GET)
	public ModelAndView displayUserDetails(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user, UserDAO userDao) throws Exception {
		
		
		HttpSession session = request.getSession(false);
		if(session!=null) {
			String username = (String)session.getAttribute("username");
			String userid = (String) session.getAttribute("userid");
			session.setAttribute("user", user);
			user = userDao.getUser(userid);
		}
		
		return new ModelAndView("user-profile", "user", user);
		
		
	}
	
	@RequestMapping(value = "/user-profile", method = RequestMethod.POST)
	public ModelAndView EditDetails(HttpServletRequest request, @ModelAttribute("user") User user,
			 UserDAO userDao) throws Exception {
		
		HttpSession session = request.getSession(false);
		if(session!=null) {
			userDao.updateUser(user);
		}		
		return new ModelAndView("user-updated");		
	}

}
