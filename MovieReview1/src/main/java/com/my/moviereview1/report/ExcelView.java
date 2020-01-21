package com.my.moviereview1.report;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;


import com.my.moviereview1.pojo.User;



public class ExcelView extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		response.setHeader("Content-Disposition", "attachment; filename= \"UserList.xls\"");
		
		List<User> list = (List<User>) model.get("list");
		Sheet sheet = workbook.createSheet("UserList");
		
		Row header = sheet.createRow(0);
		//header.createCell(0).setCellValue("ID");
		header.createCell(0).setCellValue("username");
		header.createCell(1).setCellValue("firstName");
		header.createCell(2).setCellValue("lastName");
		
		int rowNum = 1;
		
		for(User user : list) {
			Row row = sheet.createRow(rowNum++);
			//row.createCell(0).setCellValue(sales.getId());
			row.createCell(0).setCellValue(user.getUsername());
			row.createCell(1).setCellValue(user.getFirstName());
			row.createCell(2).setCellValue(user.getLastName());
			}
		
	}

}
