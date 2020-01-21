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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

//@Controller
public class AjaxController {
	
//
//@RequestMapping(value = "/ajaxservice.htm", method = RequestMethod.POST)
//@ResponseBody
//public String ajaxService(HttpServletRequest request, ModelMap map)
//{
//	int i = 10;
//	JSONArray movieSArray = (JSONArray) map.get("movieSArray");	
//	String queryString = request.getParameter("movie");
//	String result = "";
//	for(i =0;i<movieSArray.length();i++){
//		if(movieSArray.get(i).equals(queryString)){
//			result +=movieSArray.get(i);
//		}
//	}
//	
//	return result;
//}

}

