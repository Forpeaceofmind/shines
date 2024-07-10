package edu.training.web.bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Token implements Serializable {

	private static final long serialVersionUID = 1L;
	private String token;
	private LocalDateTime expirationDate;

	public Token() {
	}

	public Token(String token, LocalDateTime expirationDate) {
		this.token = token;
		this.expirationDate = expirationDate;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(expirationDate, token);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Token other = (Token) obj;
		return Objects.equals(expirationDate, other.expirationDate) && Objects.equals(token, other.token);
	}


}
