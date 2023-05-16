package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import model.Account;
import model.Post;

public class Utilities {
	static Account activeAccount = new Account(null, null);
	
	public static Account getActiveAccount() {
		return activeAccount;
	}
	
	public static void setActiveAccount(Account account) {
		activeAccount = account;
	}
	
	public static LinkedList<Post> getAccountPosts() {
		return activeAccount.getPosts();
	}

	public static LinkedList<String> spellCheck(String commentText) {
		Set<String> dictionary = new HashSet<String>();

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("dataFolder/dictionary.txt"));
			String word;
			while ((word = reader.readLine()) != null) {
				word = word.toLowerCase().replaceAll("[^a-z']", "");
				dictionary.add(word);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String[] words = commentText.split("\\s+");

		LinkedList<String> misspelledWords = new LinkedList<String>();
		for (String w : words) {
			w = w.toLowerCase().replaceAll("[^a-z']", "");
		    if (!dictionary.contains(w)) {
		        misspelledWords.add(w);
		    }
		}

		return misspelledWords;
	}
}
