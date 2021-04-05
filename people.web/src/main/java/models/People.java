package models;

import java.util.Date;

public class People {
	
	private String name;
	private Date birthDate;
	private String cpf;
	private String cep;
	private int addressNumber;
	private String complement;
	private int id;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public int getAddressNumber() {
		return addressNumber;
	}
	public void setAddressNumber(int addressNumber) {
		this.addressNumber = addressNumber;
	}
	public String getComplement() {
		return complement;
	}
	public void setComplement(String complement) {
		this.complement = complement;
	}
	public int getId() {
		return addressNumber;
	}
	public void setId(int id) {
		this.id = id;
	}
		
}