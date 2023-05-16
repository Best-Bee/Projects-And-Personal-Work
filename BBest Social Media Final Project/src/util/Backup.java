package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import model.AccountTree;

public class Backup {	
		
	public static void save(String location, AccountTree tree) {
		File file=new File(location);
		try {
			FileOutputStream fileStream = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileStream);
	             
            out.writeObject(tree.getTree());
	             
	        out.close();
	        fileStream.close();
		 }catch(FileNotFoundException e){
		 	e.printStackTrace();	
		 }catch(Exception e) {
			e.printStackTrace();
		}
	}
}