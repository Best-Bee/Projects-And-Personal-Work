package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import model.Person;
import model.PersonBag;
import model.TextbookBag;

public class Restore {
	public static PersonBag restorePersonBag() {
		try {
			FileInputStream fis = new FileInputStream("backupFolder/PersonBag.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			PersonBag personBag = (PersonBag) ois.readObject();
			Person.setIdCount((Integer)ois.readObject());
			ois.close();
			return personBag; // or
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static TextbookBag restoreTextbookBag() {
		try {
			FileInputStream fis = new FileInputStream("backupFolder/TextbookBag.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			TextbookBag textbookBag = (TextbookBag) ois.readObject();
			ois.close();
			return textbookBag; // or
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

}