package models;

import java.util.List;

public class PeopleResponse {
	private List<People> peoples;
	private int count;
	private boolean success;
	private String message; 
	
	public PeopleResponse() {
		this.success = true;
		this.message = "";
	}
	
	public PeopleResponse(List<People> peoples) {
		this.peoples = peoples;
		this.count = peoples.size();
		this.success = true;
		this.message = "";
	}
	
	public PeopleResponse(boolean success, String message) {		
		this.success = success;
		this.message = message;
	}

	public List<People> getPeoples() {
		return peoples;
	}

	public void setPeoples(List<People> peoples) {
		this.peoples = peoples;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count; 
	}
	
	public boolean getSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success; 
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message; 
	}
}
