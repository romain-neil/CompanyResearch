package fr.tutosfaciles48.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.tutosfaciles48.beans.Business;

public class DeleteBusiness extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2244568008497309730L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomEntreprise = getParameterValue(request);
		
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		Map<String, Business> business = (HashMap<String, Business>) session.getAttribute("liste");
		
		if(nomEntreprise != null && business != null) {
			business.remove(nomEntreprise);
			session.setAttribute("liste", business);
		}
		
		response.sendRedirect(request.getContextPath() + "/");
	}
	
	private String getParameterValue(HttpServletRequest request) {
		String value = request.getParameter("entreprise");
		
		if(value == null || value.trim().length() == 0) {
			return null;
		} else {
			return value;
		}
	}

}
