package fr.tutosfaciles48.forms;

import java.util.HashMap;
import java.util.Map;

import fr.tutosfaciles48.beans.Business;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class EditBusinessForm {
	
	private static final String FIELD_ADDRESS = "adresse";
	private static final String FIELD_IS_HIRING = "isHiring";
	private static final String FIELD_IS_RECOVERED = "recall";
	private static final String FIELD_NAME = "nom";
	private static final String FIELD_EMAIL = "email";
	private static final String FIELD_PHONE = "phone";
	private static final String FIELD_UUID = "uuid";
	
	public void editBusiness(HttpServletRequest request) {
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		Map<String, Business> entreprises = (HashMap<String, Business>)session.getAttribute("liste");
		Business b = entreprises.get(getFieldValue(request, FIELD_UUID)); //use uuid here
		
		String name = getFieldValue(request, FIELD_NAME);
		String address = getFieldValue(request, FIELD_ADDRESS);
		String recall = getFieldValue(request, FIELD_IS_RECOVERED);
		String isHiring = getFieldValue(request, FIELD_IS_HIRING);
		String email = getFieldValue(request, FIELD_EMAIL);
		String phone = getFieldValue(request, FIELD_PHONE);
		
		b.setName(name);
		b.setAddress(address);
		b.setRecall(recall != null);
		b.setIsHiring(isHiring != null);
		b.setEmail(email);
		b.setPhone(phone);
	}
	
	private String getFieldValue(HttpServletRequest request, String fieldName) {
		String value = request.getParameter(fieldName);
		if(value == null || value.trim().length() == 0) {
			return null;
		} else {
			return value;
		}
	}

}
