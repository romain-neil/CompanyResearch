package fr.tutosfaciles48.servlets;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import com.mkyong.utils.CSVUtils;
import fr.tutosfaciles48.beans.Business;
import fr.tutosfaciles48.util.Constants;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Export extends HttpServlet {

	@Serial
	private static final long serialVersionUID = -6493324773850616370L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession s = request.getSession();
		@SuppressWarnings("unchecked")
		Map<String, Business> entreprises = (HashMap<String, Business>) s.getAttribute("liste");
		
		if(entreprises == null || entreprises.isEmpty()) {
			s.setAttribute("message", "La liste des entreprises est vide");
			response.sendRedirect(this.getServletContext().getContextPath() + "/");
			return;
		}

		//On exporte la liste en csv
		String file = System.getProperty("java.io.tmpdir") + "/export.csv";
		FileWriter writer = new FileWriter(file);

		CSVUtils.writeLine(writer, Arrays.asList("Version", "Name", "Address", "AddedTime", "ReCall", "isHiring", "email", "phone", "Uuid"));

		for (Map.Entry<String, Business> entry : entreprises.entrySet()) {
			Business value = entry.getValue();

			CSVUtils.writeLine(writer, Arrays.asList(Integer.toString(Constants.INTERNAL_CSV_VERSION), value.getName(), value.getAddress(), value.getDateAdded().toString(), value.getRecall(), value.getIsHiring(), value.getEmail(), value.getPhone(), value.getUuid()));
		}

		writer.flush();
		writer.close();

		File downloadFile = new File(file);
		FileInputStream inStream = new FileInputStream(downloadFile);

		response.setContentType("text/plain");
		response.setHeader("Content-disposition", "attachment; filename=export-business.csv");

		ServletOutputStream outStream = response.getOutputStream();

		byte[] buffer = new byte[4096];
		int bytesRead;

		while((bytesRead = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}

		inStream.close();
		outStream.close();

		s.setAttribute("messsage", "Fichier exporté");
		response.sendRedirect(this.getServletContext().getContextPath() + "/");
	}

}
