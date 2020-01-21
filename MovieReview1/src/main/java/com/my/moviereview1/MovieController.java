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
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MovieController {
	
	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
	

	
	@RequestMapping(value = "/", method = RequestMethod.GET)	
	public ModelAndView displayMovies(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		
		ModelAndView mvc = null;	
//		for (Entry<String, Object> entry : model.entrySet()) {
//		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//		}
		String key = "3e8655395a80eddd5854026d790f304a";
		String urlBase = "https://api.themoviedb.org/3/movie/now_playing?api_key="+ key + "&language=en-US";		
		String urlSearch = "https://api.themoviedb.org/3/search/movie?api_key=" + key + "&query=";		
		String urlPopular = "https://api.themoviedb.org/3/movie/popular?api_key="+ key + "&language=en-US";
		String urlUpcoming = "https://api.themoviedb.org/3/movie/upcoming?api_key="+ key + "&language=en-US";
		
		URL url;
		HttpURLConnection con;
		BufferedReader bf;
		String output;
		
		String mov = request.getParameter("searchQuery");
				
		try {
			//collect urlbase in url
			 url = new URL(urlBase);
			 
			 //open connection
			 con =  (HttpURLConnection) url.openConnection();
			 con.setDoOutput(true);
			 con.setRequestMethod("GET");
			 con.setRequestProperty("Content-Type", "application/json");
			 
			 bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
			 
//			 while((output = bf.readLine()) !=null) {
//				// System.out.println("+++++++++++++++++++++I am output");
//				// System.out.println(output);
//			 }
			 
			 JSONObject json = readJsonFromUrl(urlBase);
			// System.out.println("I am jsonobject" +json.toString());
			 
			 JSONArray movieArray = json.getJSONArray("results");
			 
			 model.addAttribute("movieArray", movieArray);
			 
			 for(int i = 0; i < movieArray.length(); i++) {
				 //System.out.println("Json length" +movieArray.length());
				 JSONObject obj = movieArray.getJSONObject(i);
				 //System.out.println("Movie Title	" +obj.get("title"));
			 }
			 
			 mvc = new ModelAndView("movies", "movieArray", movieArray);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("MalformedURLException" + e.getMessage());
			e.printStackTrace();
		} catch(IOException e) {
			System.out.println("IO Exception" +e.getMessage());
			e.printStackTrace();
		} 
		
		try {
			//collect urlbase in url
			 url = new URL(urlUpcoming);
			 
			 //open connection
			 con =  (HttpURLConnection) url.openConnection();
			 con.setDoOutput(true);
			 con.setRequestMethod("GET");
			 con.setRequestProperty("Content-Type", "application/json");
			 
			 bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
//			 
//			 while((output = bf.readLine()) !=null) {
//				 System.out.println("+++++++++++++++++++++I am output");
//				 System.out.println(output);
//			 }
			 
			 JSONObject json = readJsonFromUrl(urlUpcoming);
			 //System.out.println("I am jsonobject" +json.toString());
			 
			 JSONArray upcomingMovieArray = json.getJSONArray("results");
			 
			 model.addAttribute("upcomingMovieArray", upcomingMovieArray);
			 
			 for(int i = 0; i < upcomingMovieArray.length(); i++) {
				 //System.out.println("Json length" +movieArray.length());
				 JSONObject obj = upcomingMovieArray.getJSONObject(i);
				 //System.out.println("Movie Title	" +obj.get("title"));
			 }
			 
			// mvc = new ModelAndView("movies", "upcomingMovieArray", upcomingMovieArray);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("MalformedURLException" + e.getMessage());
			e.printStackTrace();
		} catch(IOException e) {
			System.out.println("IO Exception" +e.getMessage());
			e.printStackTrace();
		} 
		
		
		try {
			//collect urlbase in url
			 url = new URL(urlPopular);
			 
			 //open connection
			 con =  (HttpURLConnection) url.openConnection();
			 con.setDoOutput(true);
			 con.setRequestMethod("GET");
			 con.setRequestProperty("Content-Type", "application/json");
			 
			 bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
			 
//			 while((output = bf.readLine()) !=null) {
//				 System.out.println("+++++++++++++++++++++I am output");
//				 System.out.println(output);
//			 }
			 
			 JSONObject json = readJsonFromUrl(urlPopular);
			// System.out.println("I am jsonobject" +json.toString());
			 
			 JSONArray popularMovieArray = json.getJSONArray("results");
			 
			 model.addAttribute("popularMovieArray", popularMovieArray);
			 
			 for(int i = 0; i < popularMovieArray.length(); i++) {
				 //System.out.println("Json length" +movieArray.length());
				 JSONObject obj = popularMovieArray.getJSONObject(i);
				 //System.out.println("Movie Title	" +obj.get("title"));
			 }
			 
			// mvc = new ModelAndView("movies", "popularMovieArray", popularMovieArray);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("MalformedURLException" + e.getMessage());
			e.printStackTrace();
		} catch(IOException e) {
			System.out.println("IO Exception" +e.getMessage());
			e.printStackTrace();
		} 
		
		
		if(mov != null)
		{
		try {
			//collect urlbase in url
			urlSearch = urlSearch + mov;
			 url = new URL(urlSearch);
			 
			 //open connection
			 con =  (HttpURLConnection) url.openConnection();
			 con.setDoOutput(true);
			 con.setRequestMethod("GET");
			 con.setRequestProperty("Content-Type", "application/json");
			 
			 bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
			 
//			 while((output = bf.readLine()) !=null) {
//				 System.out.println("+++++++++++++++++++++I am output");
//				 System.out.println(output);
//			 }
			 
			 JSONObject json = readJsonFromUrl(urlSearch);
			// System.out.println("I am jsonobject" +json.toString());
			 
			 JSONArray movieSArray = json.getJSONArray("results");
			 
			 model.addAttribute("movieSArray", movieSArray);
			 
			 for(int i = 0; i < movieSArray.length(); i++) {
				 //System.out.println("Json length" +movieArray.length());
				 JSONObject obj = movieSArray.getJSONObject(i);
				// System.out.println("Movie Title	" +obj.get("title"));
			 }
			 
			 //mvc = new ModelAndView("movies", "movieArray", movieArray);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("MalformedURLException" + e.getMessage());
			e.printStackTrace();
		} catch(IOException e) {
			System.out.println("IO Exception" +e.getMessage());
			e.printStackTrace();
		}
		
		HashMap<String,Object> hm= new HashMap<String,Object>();
		//hm.put(", arg1)
		}
		
		
		
		return mvc;
	}
	
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while((cp=rd.read())!=-1) {
			sb.append((char)cp);			
		}
		//System.out.println("I am StringBuilder" +sb.toString());
		return sb.toString();		
	}
	
	public static JSONObject readJsonFromUrl(String url) throws MalformedURLException, IOException {		
		InputStream ip = new URL(url).openStream();
		BufferedReader br1 = new BufferedReader(new InputStreamReader(ip, Charset.forName("UTF-8")));
		String JsonString = readAll(br1);
		JSONObject json = new JSONObject(JsonString);		
		return json;		
	}
	
	//@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String displayProfile(HttpServletRequest request) {
//		
//		return null;
//	}

}
