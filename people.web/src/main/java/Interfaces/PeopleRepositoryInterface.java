package Interfaces;

import java.util.List;

import models.People;

public interface PeopleRepositoryInterface {
	List<People> getAll();

	People getById();

	String add(List<People> peoples);

	void delete(People people);

}
