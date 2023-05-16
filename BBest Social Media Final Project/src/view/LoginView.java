package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
import model.Account;
import model.AccountTree;
import util.Backup;
import util.Utilities;

public class LoginView {
	private HBox loginPane;
	Background darkMode = new Background(new BackgroundFill(Color.rgb(70, 70, 70), null, null));
	Background darkMode3 = new Background(new BackgroundFill(Color.rgb(80, 80, 80), null, null));
	Border darkMode2 = new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, new BorderWidths(1)));
	String whiteFont = "-fx-text-fill: white;";
	
	public LoginView(AccountTree accountTree) {
		
		Button createBtn = new Button("CREATE ACCOUNT");
		createBtn.setPrefSize(200, 80);
		createBtn.setBackground(darkMode);
		createBtn.setTextFill(Color.WHITE);
		createBtn.setBorder(darkMode2);
		
		Button loginBtn = new Button("LOGIN");
		loginBtn.setPrefSize(200, 80);
		loginBtn.setBackground(darkMode);
		loginBtn.setTextFill(Color.WHITE);
		loginBtn.setBorder(darkMode2);
		
		TextField usernameField = new TextField();
		usernameField.setPromptText("username");
		usernameField.setMaxSize(150, 30);
		usernameField.setBackground(darkMode);
		usernameField.setStyle(whiteFont);
		usernameField.setBorder(darkMode2);
		
		TextField passwordField = new TextField();
		passwordField.setPromptText("password");
		passwordField.setMaxSize(150, 30);
		passwordField.setBackground(darkMode);
		passwordField.setStyle(whiteFont);
		passwordField.setBorder(darkMode2);
		
		createBtn.setOnAction(e -> {
			
			if (usernameField.getText() != "" && passwordField.getText() != "") {
				String username = usernameField.getText();
				String password = passwordField.getText();
				Account a = new Account(username, password);
				if (!accountTree.contains(username)) {
					System.out.println(username);
					System.out.println(password);
					accountTree.put(username, a);
					accountTree.get(username).addFollowing(accountTree.get("Spamton G."));
					Backup.save("backupFolder/AccountTree.dat", accountTree);
					usernameField.clear();
					passwordField.clear();
				} else {
					
				}
			} else {
				
			}
		});
		
		createBtn.setOnMouseEntered(e -> {
			createBtn.setBackground(darkMode3);
		});
		
		createBtn.setOnMouseExited(e -> {
			createBtn.setBackground(darkMode);
		});
		
		loginBtn.setOnAction(e -> {
			if (usernameField.getText() != "" && passwordField.getText() != "") {
				String username = usernameField.getText();
				String password = passwordField.getText();
				if (accountTree.get(username) != null) {
					String passCheck = accountTree.get(username).getPassword();
					if (password.equals(passCheck)) {
						Utilities.setActiveAccount(accountTree.get(username));
						System.out.println("Successfully logged in");
						System.out.println(Utilities.getActiveAccount().getUsername());
						System.out.println(Utilities.getActiveAccount().getPassword());
						System.out.println(Utilities.getActiveAccount().getFollowing());
						usernameField.clear();
						passwordField.clear();
					}
 				}
			} else {
				
			}
		});
		
		loginBtn.setOnMouseEntered(e -> {
			loginBtn.setBackground(darkMode3);
		});
		
		loginBtn.setOnMouseExited(e -> {
			loginBtn.setBackground(darkMode);
		});
		
		HBox btns = new HBox(20);
		btns.setAlignment(Pos.CENTER);
		btns.getChildren().addAll(loginBtn, createBtn);
		
		HBox input = new HBox(20);
		input.setAlignment(Pos.CENTER);
		input.getChildren().addAll(usernameField, passwordField);
		
		VBox vbox = new VBox(50);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(input, btns);
		
		loginPane = new HBox(100);
		loginPane.setAlignment(Pos.CENTER);
		loginPane.getChildren().addAll(vbox);
	}
	
	public HBox getLoginPane() {
		return loginPane;
	}
	
}
