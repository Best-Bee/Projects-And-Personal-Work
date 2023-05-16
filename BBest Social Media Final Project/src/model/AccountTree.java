package model;

import java.io.Serializable;
import java.util.TreeMap;

@SuppressWarnings({ "hiding", "serial" })
public class AccountTree implements Serializable {
	private static TreeMap<String,Account> map;
	
	public AccountTree() {
		map = new TreeMap<String,Account>();
	}
	
	public void display() {
		System.out.println(map);
		
	}
	
	public void put(String key,Account value) {
		map.put(key, value);
	}
	
	public TreeMap<String, Account> getTree() {
		return map;
	}
	
	public void setTree(TreeMap<String, Account> map){
		if(map!=null) {
			this.map=map;
		}
	}

	public Account get(String username) {
		return map.get(username);
	}	
	
	public boolean contains(String username) {
		return map.containsKey(username);
	}
}
