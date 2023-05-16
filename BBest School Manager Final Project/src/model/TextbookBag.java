package model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.function.Predicate;

public class TextbookBag implements Serializable{
	private static final long serialVersionUID = 1L;
	private Textbook[] arr;
	private int nElems;
	
	public TextbookBag(int maxSize) {
		arr = new Textbook[maxSize];
	}
	
	public void insert(Textbook textbook) {
		arr[nElems++] = textbook;
	}
	
	public Textbook[] search(String isbn, String firstName, String lastName) {
		Textbook[] temp = new Textbook[nElems];
		int count = 0;
		for (int i = 0; i < nElems; i++) {
			if(String.valueOf(isbn).equals(String.valueOf(arr[i].getIsbn()))) {
				temp[count++] = arr[i];
			}
			if((String.valueOf(firstName).equals(String.valueOf(arr[i].getAuthor().getFirstName()))) || (String.valueOf(lastName).equals(String.valueOf(arr[i].getAuthor().getLastName())))) {
				temp[count++] = arr[i];
			}
		}
		return Arrays.copyOf(temp, count);
	}
	
	public Textbook[] delete(String isbn) {
		Textbook[] temp = new Textbook[nElems];
		int count = 0;
		for (int i = 0; i < nElems; i++) {
			if(String.valueOf(isbn).equals(String.valueOf(arr[i].getIsbn()))) {
				temp[count++] = arr[i];
				for(int j = i; j < nElems-1; j++) {
					arr[j] = arr[j+1];
				}
				nElems--;
				i--;
			}
		}
		return Arrays.copyOf(temp, count);
	}
	
	public void update(String isbn, String title, String firstName, String lastName, double price) {
		for (int i = 0; i < nElems; i++) {
			if (String.valueOf(isbn).equals(String.valueOf(arr[i].getIsbn()))) {
				if (arr[i] instanceof Textbook) {
					arr[i] = new Textbook(title, isbn, new Name(firstName, lastName), price);
				}
			}
		}
	}
	
	public void display() {
		for(int i = 0; i < nElems; i++) {
			System.out.println(arr[i]);
		}
		System.out.println();
	}
	
	public int getnElems() {
		return nElems;
	}
}
