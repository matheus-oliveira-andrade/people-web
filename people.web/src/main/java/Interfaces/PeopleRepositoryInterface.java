package Interfaces;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import models.People;

public interface PeopleRepositoryInterface {
	List<People> getAll(String idDocument) throws UnknownHostException;

	People getById(String idDocument, int index) throws UnknownHostException;

	String add(List<People> peoples) throws UnknownHostException;

	void edit(List<People> peoples, String idDocument) throws UnknownHostException;

	void delete(People people, String idDocument) throws UnknownHostException;

}
