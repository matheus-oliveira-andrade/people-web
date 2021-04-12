package models;

public class Address {
	private String cep;
	private String street;
	private String complement;
	private String district;
	private String locality;
	private String uf;

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getcomplement() {
		return complement;
	}

	public void setcomplement(String complement) {
		this.complement = complement;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@Override
	public String toString() {
		return "Address [cep=" + cep + ", street=" + street + ", complement=" + complement + ", district=" + district
				+ ", locality=" + locality + ", uf=" + uf + "]";
	}
	
}
