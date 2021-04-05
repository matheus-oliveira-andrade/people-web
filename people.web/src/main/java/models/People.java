package models;

import java.util.Date;

public class People {
	
	private String Name;
	private Date BirthDate;
	private String Cpf;
	private String Cep;
	private int AddressNumber;
	private String Complement;
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Date getBirthDate() {
		return BirthDate;
	}
	public void setBirthDate(Date birthDate) {
		BirthDate = birthDate;
	}
	public String getCpf() {
		return Cpf;
	}
	public void setCpf(String cpf) {
		Cpf = cpf;
	}
	public String getCep() {
		return Cep;
	}
	public void setCep(String cep) {
		Cep = cep;
	}
	public int getAddressNumber() {
		return AddressNumber;
	}
	public void setAddressNumber(int addressNumber) {
		AddressNumber = addressNumber;
	}
	public String getComplement() {
		return Complement;
	}
	public void setComplement(String complement) {
		Complement = complement;
	}
		
}