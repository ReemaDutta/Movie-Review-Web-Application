package com.my.moviereview1.filter;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.log4j.Logger;



public class RequestWrapper  extends HttpServletRequestWrapper {
	
	private static Logger logger = Logger.getLogger(RequestWrapper.class);
	static String allowedChars = "+-0123456789#*";
	public RequestWrapper(ServletRequest servletRequest) {
		super((HttpServletRequest)servletRequest);
		
	}
	
	
	
	public String[] getParameterValues(String parameter) {
		logger.info("InarameterValues .. parameter .......");
		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}
		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = cleanXSS(values[i]);
		}
		return encodedValues;
	}

	
	public String getParameter(String parameter) {
		logger.info("Inarameter .. parameter .......");
		String value = super.getParameter(parameter);
		if (value == null) {
			return null;
		}
		logger.info("Inarameter RequestWrapper ........ value .......");
		return cleanXSS(value);
	}

	public String getHeader(String name) {
		logger.info("Ineader .. parameter .......");
		String value = super.getHeader(name);
		if (value == null)
			return null;
		logger.info("Ineader RequestWrapper ........... value ....");
		return cleanXSS(value);
	}

	private String cleanXSS(String value) {
		// You'll need to remove the spaces from the html entities below
		logger.info("InnXSS RequestWrapper ..............." + value);
		value = value.replaceAll("<", " ").replaceAll(">", " ");
		value = value.replaceAll("=", " ");
		value = value.replaceAll("/", " ");
		value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
		value = value.replaceAll("'", "& #39;");
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");

		value = value.replaceAll("(?i)<script.*?>.*?<script.*?>", "");
		value = value.replaceAll("(?i)<script.*?>.*?</script.*?>", "");
		value = value.replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "");
		value = value.replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "");
		value = value.replaceAll("<script>", "");
		value = value.replaceAll("</script>", "");
		logger.info("OutnXSS RequestWrapper ........ value ......." + value);
		return value;
	}
	
	
	

}

