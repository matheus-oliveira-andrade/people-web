package models;

import java.util.List;

public class PeopleResponse {
	private List<People> peoples;
	private int count;

	public PeopleResponse(List<People> peoples) {
		this.peoples = peoples;
		this.count = peoples.size();
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
}
