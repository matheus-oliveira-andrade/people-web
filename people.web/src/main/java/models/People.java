package models;

import java.util.Date;

public class People {
	
	private int id;
	private String name;
	private Date birthDate;
	private String cpf;
	private String cep;
	private int addressNumber;
	private String complement;	
	
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
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "People [id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", cpf=" + cpf + ", cep=" + cep
				+ ", addressNumber=" + addressNumber + ", complement=" + complement + ", getName()=" + getName()
				+ ", getBirthDate()=" + getBirthDate() + ", getCpf()=" + getCpf() + ", getCep()=" + getCep()
				+ ", getAddressNumber()=" + getAddressNumber() + ", getComplement()=" + getComplement() + ", getId()="
				+ getId() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
		
	
}