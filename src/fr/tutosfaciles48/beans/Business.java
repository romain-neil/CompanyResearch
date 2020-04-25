package fr.tutosfaciles48.beans;

import org.joda.time.DateTime;

public class Business {
	
	private DateTime dateAdded;
	private DateTime recoveryDate;
	
	private String name;
	private String address;
	
	private String recall;
	
	private String uuid;
	private String isHiring;
	
	/**
	 * @return the dateAdded
	 */
	public DateTime getDateAdded() {
		return dateAdded;
	}
	
	/**
	 * @param dateAdded the dateAdded to set
	 */
	public void setDateAdded(DateTime dateAdded) {
		this.dateAdded = dateAdded;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param string the address to set
	 */
	public void setAddress(String string) {
		this.address = string;
	}

	/**
	 * @return string recall
	 */
	public String getRecall() {
		return recall;
	}

	/**
	 * @param recall the isRecovered to set
	 */
	public void setRecall(boolean recall) {
		this.recall = recall ? "Oui" : "Non";
	}

	/**
	 * @return string the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * @return the isHiring
	 */
	public String getIsHiring() {
		if(isHiring == null) {
			return "-";
		}
		return isHiring;
	}

	/**
	 * @param isHiring the isHiring to set
	 */
	public void setIsHiring(boolean isHiring) {
		this.isHiring = isHiring ? "Oui" : "Non";
	}

}
