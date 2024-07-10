package edu.training.web.bean;

import java.util.Objects;

public class UserRegInfo {
	private long id;
	private String login;
	private String password;
	private String name;
	private String birthDate;
	private String residence;
	

	public UserRegInfo() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getResidence() {
		return residence;
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthDate, id, login, name, password, residence);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRegInfo other = (UserRegInfo) obj;
		return Objects.equals(birthDate, other.birthDate) && id == other.id && Objects.equals(login, other.login)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(residence, other.residence);
	}

	@Override
	public String toString() {
		return "UserRegInfo [id=" + id + ", login=" + login + ", password=" + password + ", name=" + name
				+ ", birthDate=" + birthDate + ", residence=" + residence + "]";
	}

	

}