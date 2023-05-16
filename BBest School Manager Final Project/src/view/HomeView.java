package view;

import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class HomeView {
	private HBox homePane;
	private static final int BUFFER = 20;
	Border darkMode2 = new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, new BorderWidths(1)));
	
	public HomeView() {
		TextArea outputArea = new TextArea();
		outputArea.setStyle("-fx-control-inner-background:#404040;-fx-text-fill: white;");
		outputArea.setMaxSize(600, 300);
		outputArea.setMinSize(600, 300);
		outputArea.setBorder(darkMode2);
		
		VBox ioBox = new VBox(BUFFER);
		ioBox.setAlignment(Pos.CENTER);
		ioBox.getChildren().addAll(outputArea);
		
		outputArea.appendText("Hello and welcome to my program, the main purpose of this is to be able to search, update, insert and"
				+ "\nremove students, instructors and textbooks."
				+ "\n\nGuide:"
				
				+ "\n\tStudents:"
				+ "\n\t\tInsert: will use all fields given except for id to create a new student, blank fields will be set to a"
				+ "\n\t\tdefault value"
				+ "\n\t\tSearch: will use first name, last name and id and will return all students that match any on the"
				+ "\n\t\tgiven values"
				+ "\n\t\tUpdate: will use all fields, id specifies which student to update, the other fields are the new values"
				+ "\n\t\tgiven to the student"
				+ "\n\t\tRemove: will use first and last name, and id, will delete any student with the given full name or id"
				
				+ "\n\n\tInstructors:"
				+ "\n\t\tInsert: will use all fields given except for id to create a new instructor, blank fields will be set to"
				+ "\n\t\ta default value"
				+ "\n\t\tSearch: will use first name, last name and id and will return all instructors that match any of the"
				+ "\n\t\tgiven values"
				+ "\n\t\tUpdate: will use all fields, id specifies which instructor to update, the other fields are the new"
				+ "\n\t\tvalues given to the instructor"
				+ "\n\t\tRemove: will use first and last name, and id, will delete any instructor with the given full name or "
				+ "\n\t\tid"
		
				+ "\n\n\tTextbooks:"
				+ "\n\t\tInsert: will use all fields given to create a new textbook, blank fields will be set to a default"
				+ "\n\t\tvalue"
				+ "\n\t\tSearch: will use first name, last name, and isbn and will return all textbooks that match any of the"
				+ "\n\t\tgiven values"
				+ "\n\t\tUpdate: will use all fields, isbn specifies which textbook to update, the other fields are the new"
				+ "\n\t\tvalues given to the textbook"
				+ "\n\t\tRemove: will use isbn, will delete any textbook with the given isbn"
				
				+ "\n\n***Default values are as follows:"
				+ "\nFirst Name: Null"
				+ "\nLast Name: Null"
				+ "\nGpa: 0.0"
				+ "\nMajor: Null"
				+ "\nSalary: 0.0"
				+ "\nTitle: Null"
				+ "\nISBN: 0-00-000-0000"
				+ "\nPrice: 0.0");

		homePane = new HBox(BUFFER);
		homePane.setAlignment(Pos.CENTER);
		homePane.getChildren().addAll(ioBox);
	}
	
	public HBox getHomePane() {
		return homePane;
	}
}
