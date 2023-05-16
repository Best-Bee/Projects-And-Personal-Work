package model;

import java.io.Serializable;
import java.util.LinkedList;

public class Account implements Serializable{
	private String username;
	private String password;
	private LinkedList<Post> posts = new LinkedList<Post>();
	private LinkedList<Account> following = new LinkedList<Account>();
	
	public String getUsername() {
		return username;
	}
	public LinkedList<Post> getPosts() {
		return posts;
	}
	public LinkedList<Account> getFollowing() {
		return following;
	}
	public void addFollowing(Account account) {
		following.add(account);
	}
	public void addPost(Post post) {
		posts.add(post);
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Account(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getFollowedAccounts() {
		String follow = "";
		for (Account account : following) {
			follow += account.getUsername() + "\n";
		}
		return follow;
	}
}
