package view;

import java.util.function.Predicate;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Name;
import model.Person;
import model.PersonBag;
import model.Student;
import util.Backup;

public class StudentView {
	private PersonBag personBag;
	private HBox studentPane;
	private static final int BUFFER = 20;
	Background darkMode = new Background(new BackgroundFill(Color.rgb(70, 70, 70), null, null));
	Border darkMode2 = new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, new BorderWidths(1)));
	String whiteFont = "-fx-text-fill: white;";
	
	public StudentView(PersonBag personBag) {
		this.personBag = personBag;
		
		Button insertBtn = new Button("INSERT");
		insertBtn.setPrefSize(100, 30);
		insertBtn.setBackground(darkMode);
		insertBtn.setTextFill(Color.WHITE);
		insertBtn.setBorder(darkMode2);
		
		Button searchBtn = new Button("SEARCH");
		searchBtn.setPrefSize(100, 30);
		searchBtn.setBackground(darkMode);
		searchBtn.setTextFill(Color.WHITE);
		searchBtn.setBorder(darkMode2);
		
		Button updateBtn = new Button("UPDATE");
		updateBtn.setPrefSize(100, 30);
		updateBtn.setBackground(darkMode);
		updateBtn.setTextFill(Color.WHITE);
		updateBtn.setBorder(darkMode2);
		
		Button removeBtn = new Button("REMOVE");
		removeBtn.setPrefSize(100, 30);
		removeBtn.setBackground(darkMode);
		removeBtn.setTextFill(Color.WHITE);
		removeBtn.setBorder(darkMode2);
		
		VBox btnBox = new VBox(BUFFER);
		btnBox.setAlignment(Pos.CENTER);
		btnBox.getChildren().addAll(insertBtn, searchBtn, updateBtn, removeBtn);
		
		TextField firstNameField = new TextField();
		firstNameField.setPromptText("First Name");
		firstNameField.setPrefSize(150, 30);
		firstNameField.setBackground(darkMode);
		firstNameField.setStyle(whiteFont);
		firstNameField.setBorder(darkMode2);
		
		TextField lastNameField = new TextField();
		lastNameField.setPromptText("Last Name");
		lastNameField.setPrefSize(150, 30);
		lastNameField.setBackground(darkMode);
		lastNameField.setStyle(whiteFont);
		lastNameField.setBorder(darkMode2);
		
		TextField gpaField = new TextField();
		gpaField.setPrefSize(150, 30);
		gpaField.setPromptText("GPA");
		gpaField.setBackground(darkMode);
		gpaField.setStyle(whiteFont);
		gpaField.setBorder(darkMode2);
		
		TextField majorField = new TextField();
		majorField.setPrefSize(150, 30);
		majorField.setPromptText("Major");
		majorField.setBackground(darkMode);
		majorField.setStyle(whiteFont);
		majorField.setBorder(darkMode2);
		
		TextField idField = new TextField();
		idField.setPrefSize(150, 30);
		idField.setPromptText("ID");
		idField.setBackground(darkMode);
		idField.setStyle(whiteFont);
		idField.setBorder(darkMode2);
		
		HBox inputBox1 = new HBox(BUFFER);
		inputBox1.setAlignment(Pos.CENTER);
		inputBox1.getChildren().addAll(firstNameField, lastNameField);
		
		HBox inputBox2 = new HBox(BUFFER);
		inputBox2.setAlignment(Pos.CENTER);
		inputBox2.getChildren().addAll(gpaField, majorField, idField);
		
		VBox input = new VBox(BUFFER);
		input.setAlignment(Pos.CENTER);
		input.getChildren().addAll(inputBox1, inputBox2);
		
		TextArea outputArea = new TextArea();
		outputArea.setStyle("-fx-control-inner-background:#404040;-fx-text-fill: white;");
		outputArea.setMaxSize(600, 300);
		outputArea.setBorder(darkMode2);
		
		VBox ioBox = new VBox(BUFFER);
		ioBox.setAlignment(Pos.CENTER);
		ioBox.getChildren().addAll(input, outputArea);
		
		insertBtn.setOnAction(e -> {
			if (firstNameField.getText() == "") {
				firstNameField.setText("null");
			}
			if (lastNameField.getText() == "") {
				lastNameField.setText("null");
			}
			if (majorField.getText() == "") {
				majorField.setText("null");
			}
			if (gpaField.getText() == "") {
				gpaField.setText("0.0");
			}
			String firstName = firstNameField.getText();
			String lastName = lastNameField.getText();
			String major = majorField.getText();
			double gpa = Double.parseDouble(gpaField.getText());
			Student s = new Student(new Name(firstName, lastName), gpa, major);
			personBag.insertStudent(s);
			Backup.backupPersonBag(personBag);
			firstNameField.clear();
			lastNameField.clear();
			gpaField.clear();
			majorField.clear();
			idField.clear();
			outputArea.appendText("First Name: " + firstName + "\nLast Name: " + lastName + "\nGPA: " + gpa + "\nMajor: " + major + "\n\n");
		});
		
		searchBtn.setOnAction(e -> {
			if (firstNameField.getText() == "") {
				firstNameField.setText("null");
			}
			if (lastNameField.getText() == "") {
				lastNameField.setText("null");
			}
			if (idField.getText() == "") {
				idField.setText("-1");
			}
			String firstName = firstNameField.getText();
			String lastName = lastNameField.getText();
			Student[] studentsFound = personBag.searchStudents((Person s) -> {
				return s.getName().getFirstName().equalsIgnoreCase(firstName) || s.getName().getLastName().equalsIgnoreCase(lastName) || s.getId().equals(String.valueOf(Integer.parseInt(idField.getText())));
			});
			firstNameField.clear();
			lastNameField.clear();
			gpaField.clear();
			majorField.clear();
			idField.clear();
			outputArea.appendText("Students Found:\n");
			for (int i = 0; i < studentsFound.length; i++) {
				outputArea.appendText("First Name: " + studentsFound[i].getName().getFirstName() + "\nLast Name: " + studentsFound[i].getName().getLastName() + "\nID: " + studentsFound[i].getId() + "\nGPA: " + studentsFound[i].getGpa() + "\nMajor: " + studentsFound[i].getMajor() + "\n\n");
			}
		});
		
		removeBtn.setOnAction(e -> {
			if (firstNameField.getText() == "") {
				firstNameField.setText("null");
			}
			if (lastNameField.getText() == "") {
				lastNameField.setText("null");
			}
			if (idField.getText() == "") {
				idField.setText("-1");
			}
			int id = Integer.parseInt(idField.getText());
			String firstName = firstNameField.getText();
			String lastName = lastNameField.getText();
			personBag.deleteStudents(id, firstName, lastName);
			firstNameField.clear();
			lastNameField.clear();
			gpaField.clear();
			majorField.clear();
			idField.clear();
			Backup.backupPersonBag(personBag);
			outputArea.appendText("Students deleted with name: " + firstName + " " + lastName + " or with ID: " + id + "\n\n");
		});

		updateBtn.setOnAction(e -> {
			if (firstNameField.getText() == "") {
				firstNameField.setText("null");
			}
			if (lastNameField.getText() == "") {
				lastNameField.setText("null");
			}
			if (idField.getText() == "") {
				idField.setText("-1");
			}
			int id = Integer.parseInt(idField.getText());
			String firstName = firstNameField.getText();
			String lastName = lastNameField.getText();
			String major = majorField.getText();
			double gpa = Double.parseDouble(gpaField.getText());
			personBag.updateStudent(id, firstName, lastName, gpa, major);
			Backup.backupPersonBag(personBag);
			firstNameField.clear();
			lastNameField.clear();
			gpaField.clear();
			majorField.clear();
			idField.clear();
		});
			
		studentPane = new HBox(BUFFER);
		studentPane.setAlignment(Pos.CENTER);
		studentPane.getChildren().addAll(btnBox, ioBox);
	}
	
	public HBox getStudentPane() {
		return studentPane;
	}
	
}
