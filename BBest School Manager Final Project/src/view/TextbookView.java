package view;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Name;
import model.Person;
import model.PersonBag;
import model.Student;
import model.Textbook;
import model.TextbookBag;
import util.Backup;

public class TextbookView {
	private TextbookBag textbookBag;
	private HBox textbookPane;
	private static final int BUFFER = 20;
	Background darkMode = new Background(new BackgroundFill(Color.rgb(70, 70, 70), null, null));
	Border darkMode2 = new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, new BorderWidths(1)));
	String whiteFont = "-fx-text-fill: white;";
	
	public TextbookView(TextbookBag textbookBag) {
		this.textbookBag = textbookBag;
		
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
		
		TextField isbnField = new TextField();
		isbnField.setPrefSize(150, 30);
		isbnField.setPromptText("ISBN");
		isbnField.setBackground(darkMode);
		isbnField.setStyle(whiteFont);
		isbnField.setBorder(darkMode2);
		
		TextField titleField = new TextField();
		titleField.setPrefSize(150, 30);
		titleField.setPromptText("Title");
		titleField.setBackground(darkMode);
		titleField.setStyle(whiteFont);
		titleField.setBorder(darkMode2);
		
		TextField costField = new TextField();
		costField.setPrefSize(150, 30);
		costField.setPromptText("Cost");
		costField.setBackground(darkMode);
		costField.setStyle(whiteFont);
		costField.setBorder(darkMode2);
		
		HBox inputBox1 = new HBox(BUFFER);
		inputBox1.setAlignment(Pos.CENTER);
		inputBox1.getChildren().addAll(firstNameField, lastNameField);
		
		HBox inputBox2 = new HBox(BUFFER);
		inputBox2.setAlignment(Pos.CENTER);
		inputBox2.getChildren().addAll(isbnField, titleField, costField);
		
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
			if (titleField.getText() == "") {
				titleField.setText("null");
			}
			if (costField.getText() == "") {
				costField.setText("0.0");
			}
			if (isbnField.getText() == "") {
				isbnField.setText("0-00-000-0000");
			}
			String firstName = firstNameField.getText();
			String lastName = lastNameField.getText();
			String title = titleField.getText();
			String isbn = isbnField.getText();
			double cost = Double.parseDouble(costField.getText());
			Textbook t = new Textbook(title, isbn, new Name(firstName, lastName), cost);
			textbookBag.insert(t);
			Backup.backupTextbookBag(textbookBag);
			firstNameField.clear();
			lastNameField.clear();
			isbnField.clear();
			costField.clear();
			titleField.clear();
			outputArea.appendText("First Name: " + firstName + "\nLast Name: " + lastName + "\nCost: " + cost + "\nTitle: " + title + "\nISBN: " + isbn + "\n\n");
		});
		
		searchBtn.setOnAction(e -> {
			if (isbnField.getText() == "") {
				isbnField.setText("00000000");
			}
			if (firstNameField.getText() == "") {
				firstNameField.setText("null");
			}
			if (lastNameField.getText() == "") {
				lastNameField.setText("null");
			}
			String isbn = isbnField.getText();
			String firstName = firstNameField.getText();
			String lastName = lastNameField.getText();
			Textbook[] textbooksFound = textbookBag.search(isbn, firstName, lastName);
			System.out.println(isbn);
			firstNameField.clear();
			lastNameField.clear();
			costField.clear();
			isbnField.clear();
			titleField.clear();
			outputArea.appendText("Textbooks Found:\n");
			for (int i = 0; i < textbooksFound.length; i++) {
				outputArea.appendText("First Name: " + textbooksFound[i].getAuthor().getFirstName() + "\nLast Name: " + textbooksFound[i].getAuthor().getLastName() + "\nTITLE: " + textbooksFound[i].getTitle() + "\nCost: " + textbooksFound[i].getPrice() + "\nISBN: " + textbooksFound[i].getIsbn() + "\n\n");
			}
		});
		
		removeBtn.setOnAction(e -> {
			if (isbnField.getText() == "") {
				isbnField.setText("00000000");
			}
			String isbn = isbnField.getText();
			Textbook[] textbooksFound = textbookBag.delete(isbn);
			firstNameField.clear();
			lastNameField.clear();
			titleField.clear();
			costField.clear();
			isbnField.clear();
			Backup.backupTextbookBag(textbookBag);
			outputArea.appendText("Textbook deleted with isbn: " + isbn + "\n\n");
		});

		updateBtn.setOnAction(e -> {
			if (firstNameField.getText() == "") {
				firstNameField.setText("null");
			}
			if (lastNameField.getText() == "") {
				lastNameField.setText("null");
			}
			if (isbnField.getText() == "") {
				isbnField.setText("00000000");
			}
			String isbn = isbnField.getText();
			String firstName = firstNameField.getText();
			String lastName = lastNameField.getText();
			String title = titleField.getText();
			double cost = Double.parseDouble(costField.getText());
			textbookBag.update(isbn, title, firstName, lastName, cost);
			Backup.backupTextbookBag(textbookBag);
			firstNameField.clear();
			lastNameField.clear();
			costField.clear();
			titleField.clear();
			isbnField.clear();
		});
			
		textbookPane = new HBox(BUFFER);
		textbookPane.setAlignment(Pos.CENTER);
		textbookPane.getChildren().addAll(btnBox, ioBox);
	}
	
	public HBox getTextbookPane() {
		return textbookPane;
	}
}
