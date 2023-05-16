package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Comment implements Serializable{
	private Account poster;
	private LocalDateTime date;
	private String body;
	
	public Comment(Account poster, LocalDateTime date, String body) {
		super();
		this.poster = poster;
		this.date = date;
		this.body = body;
	}

	public Account getPoster() {
		return poster;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}	
	
}
