package com.my.moviereview1.report;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;


import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import com.my.moviereview1.pojo.User;

public class PdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document pdfdoc, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		List<User> list = (List<User>) model.get("list");
		
		for(User user : list) {
			pdfdoc.add(new Paragraph("User Name: " + user.getUsername()));
			pdfdoc.add(new Paragraph("First Name: " + user.getFirstName()));
			pdfdoc.add(new Paragraph("Last Name: " + user.getLastName()));			
		}
	}

}
