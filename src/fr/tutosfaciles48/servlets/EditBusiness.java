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

import fr.tutosfaciles48.forms.EditBusinessForm;

public class EditBusiness extends HttpServlet {
	
	private static final String VUE = "/WEB-INF/edit.jsp";

	/**
	 * 
	 */
	private static final long serialVersionUID = 5799950166871570064L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String CompanyId = request.getParameter("id");
		HttpSession session = request.getSession();
		
		if(CompanyId == null || CompanyId.trim().length() == 0) {
			response.sendRedirect(this.getServletContext().getContextPath() + "/");
		} else {
			@SuppressWarnings("unchecked")
			Map<String, Business> entreprises = (HashMap<String, Business>) session.getAttribute("liste");
			Business b = entreprises.get(CompanyId);
			
			if(b == null) {
				response.sendRedirect(this.getServletContext().getContextPath() + "/");
				return;
			}
			
			request.setAttribute("ent", b);
			
			System.out.println("Editing company " + b.getName());
			
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EditBusinessForm form = new EditBusinessForm();
		
		form.editBusiness(request);
		
		response.sendRedirect(this.getServletContext().getContextPath() + "/");
	}

}
