package fr.tutosfaciles48.forms;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;

import fr.tutosfaciles48.beans.Business;

public class CreateBusinessForm {
	
	private static final String FIELD_ADDRESS = "adresse";
	private static final String FIELD_IS_RECOVERED = "recall";
	private static final String FIELD_NAME = "nom";
	//private static final String FIELD_DATE_RECOVERY = "dateRecovery";
	public static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
	
	private String result;
	private Map<String, String> errors = new HashMap<String, String>();
	
	public String getResult() {
		return result;
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}
	
	public Business createBusiness(HttpServletRequest request) {
		Business b = new Business();
		
		b.setAddress(this.getFieldValue(request, FIELD_ADDRESS));
		
		String reCall = this.getFieldValue(request, FIELD_IS_RECOVERED);
		
		if(reCall != null) {
			b.setRecall(true);
		} else {
			b.setRecall(false);
		}
		
		b.setName(this.getFieldValue(request, FIELD_NAME));
		b.setDateAdded(new DateTime());
		b.setUuid(UUID.randomUUID().toString());
		
		return b;
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
