package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.TreeMap;
import model.Account;

import model.AccountTree;

public class Restore {
	@SuppressWarnings("unchecked")
	public static void load(String location, AccountTree tree) {
		File file=new File(location);
		try {
			if(!file.exists())return;
			 FileInputStream fileStream = new FileInputStream(file);
	         ObjectInputStream in = new ObjectInputStream(fileStream);
	             
	         tree.setTree((TreeMap<String,Account>) in.readObject());
	             
	         in.close();
	         fileStream.close();
		 }catch(FileNotFoundException e){
		 	e.printStackTrace();	
		 }catch(Exception e) {
			e.printStackTrace();
		}
	}
}