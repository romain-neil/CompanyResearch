package fr.tutosfaciles48.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import fr.tutosfaciles48.beans.Business;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DeleteBusiness extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String businessId = getParameterValue(request);

		if(businessId == null) {
			response.sendRedirect(request.getContextPath() + "/");
			return;
		}
		
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		Map<String, Business> business = (HashMap<String, Business>) session.getAttribute("liste");
		business.remove(businessId);
		session.setAttribute("liste", business);
		
		response.sendRedirect(request.getContextPath() + "/");
	}
	
	private String getParameterValue(HttpServletRequest request) {
		String value = request.getParameter("id");
		
		if(value == null || value.trim().length() == 0) {
			return null;
		} else {
			return value;
		}
	}

}
