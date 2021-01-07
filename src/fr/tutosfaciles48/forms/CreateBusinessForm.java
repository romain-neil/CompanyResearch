package fr.tutosfaciles48.forms;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;

import fr.tutosfaciles48.beans.Business;

public class CreateBusinessForm {
	
	private static final String FIELD_ADDRESS = "adresse";
	private static final String FIELD_IS_RECOVERED = "recall";
	private static final String FIELD_NAME = "nom";
	private static final String FIELD_EMAIL = "email";
	private static final String FIELD_PHONE = "phone";

	private String lastError = "";

	public boolean hasError() {
		return lastError.isBlank();
	}
	
	public String getError() {
		return lastError;
	}
	
	public Business createBusiness(HttpServletRequest request) {
		Business b = new Business();

		String name = this.getFieldValue(request, FIELD_NAME);
		String address = this.getFieldValue(request, FIELD_ADDRESS);
		String reCall = this.getFieldValue(request, FIELD_IS_RECOVERED);
		String email = getFieldValue(request, FIELD_EMAIL);
		String phone = getFieldValue(request, FIELD_PHONE);

		if(!name.isBlank() && !address.isBlank()) {
			b.setAddress(address);
			b.setRecall(reCall.equals("true"));
			b.setName(name);
			b.setEmail(email);
			b.setPhone(phone);

			b.setDateAdded(new DateTime());
			b.setUuid(UUID.randomUUID().toString());

			return b;
		}

		lastError = "Un ou plusieurs champs sont vides";

		return null;
	}
	
	private String getFieldValue(HttpServletRequest request, String fieldName) {
		String value = request.getParameter(fieldName);
		if(value == null || value.trim().length() == 0) {
			return "";
		} else {
			return value;
		}
	}

}
