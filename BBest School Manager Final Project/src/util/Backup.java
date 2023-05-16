package util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import model.PersonBag;
import model.TextbookBag;

public class Backup {
	
	public static void backupPersonBag(PersonBag personBag) {
		try {
			FileOutputStream fos = new FileOutputStream("backupFolder/PersonBag.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(personBag);
			oos.writeObject(personBag.getnElems());
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void backupTextbookBag(TextbookBag textbookBag) {
		try {
			FileOutputStream fos = new FileOutputStream("backupFolder/TextbookBag.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(textbookBag);
			oos.writeObject(textbookBag.getnElems());
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}