package fr.tutosfaciles48.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mkyong.utils.CSVUtils;
import fr.tutosfaciles48.beans.Business;
import fr.tutosfaciles48.util.Constants;

public class Export extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6493324773850616370L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s = request.getSession();
		@SuppressWarnings("unchecked")
		Map<String, Business> entreprises = (HashMap<String, Business>) s.getAttribute("liste");
		
		if(entreprises == null || entreprises.isEmpty()) {
			response.sendRedirect(this.getServletContext().getContextPath() + "/");
			return;
		}
		
		//if(request.getAttribute("pdf") != null) {
			//Export as pdf
		//} else {
			//On exporte la liste en csv
			String file = "C:/tmp/export.csv";
			FileWriter writer = new FileWriter(file);
			
			CSVUtils.writeLine(writer, Arrays.asList("Name", "Address", "AddedTime", "ReCall", "isHiring", "Uuid"));
			
			for (Map.Entry<String, Business> entry : entreprises.entrySet()) {
			    Business value = entry.getValue();
			    
			    String isHiring = value.getIsHiring();
			    boolean hiring = false;
			    if(isHiring.toLowerCase().equals("oui")) {
			    	hiring = true;
			    }
			    
			    CSVUtils.writeLine(writer, Arrays.asList(Integer.toString(Constants.INTERNAL_CSV_VERSION), value.getName(), value.getAddress(), value.getDateAdded().toString(), value.getRecall(), Boolean.toString(hiring), value.getUuid()));
			}
			
			writer.flush();
			writer.close();
			
			File downloadFile = new File(file);
			FileInputStream inStream = new FileInputStream(downloadFile);
			
			response.setContentType("text/plain");
			response.setHeader("Content-disposition", "attachment; filename=export-business.csv");
			
			ServletOutputStream outStream = response.getOutputStream();
			
			byte[] buffer = new byte[4096];
			int bytesRead = -1;
			
			while((bytesRead = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			
			inStream.close();
			outStream.close();
		//}
		
		response.sendRedirect(this.getServletContext().getContextPath() + "/");
	}

}
