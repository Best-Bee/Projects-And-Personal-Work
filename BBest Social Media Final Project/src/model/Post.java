package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class Post implements Serializable{
	private Account poster;
	private LocalDateTime date;
	private String body;
	private String header;
	private LinkedList<Comment> comments = new LinkedList<Comment>();
	
	public Post(Account poster, LocalDateTime date, String header, String body) {
		super();
		this.poster = poster;
		this.date = date;
		this.body = body;
		this.header = header;
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

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}
	public void addComment(Comment comment) {
		comments.add(comment);
	}
	public LinkedList<Comment> getComments() {
		return comments;
	}
	
	
}
