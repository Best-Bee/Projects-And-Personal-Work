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

public class FollowView {
	private HBox followPane;
	Background darkMode = new Background(new BackgroundFill(Color.rgb(70, 70, 70), null, null));
	Background darkMode3 = new Background(new BackgroundFill(Color.rgb(80, 80, 80), null, null));
	Border darkMode2 = new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, new BorderWidths(1)));
	String whiteFont = "-fx-text-fill: white;";
	
	public FollowView(AccountTree accountTree) {
		
		Button searchBtn = new Button("Follow");
		searchBtn.setPrefSize(200, 80);
		searchBtn.setBackground(darkMode);
		searchBtn.setTextFill(Color.WHITE);
		searchBtn.setBorder(darkMode2);
		
		TextField usernameField = new TextField();
		usernameField.setPromptText("username to follow");
		usernameField.setMaxSize(150, 30);
		usernameField.setBackground(darkMode);
		usernameField.setStyle(whiteFont);
		usernameField.setBorder(darkMode2);
		
		searchBtn.setOnAction(e -> {
			
			if (usernameField.getText() != "") {
				String username = usernameField.getText();
				if (accountTree.contains(username)) {
					Account a = accountTree.get(username);
					Utilities.getActiveAccount().addFollowing(a);
					System.out.println("Followed " + username);
					Backup.save("backupFolder/AccountTree.dat", accountTree);
					usernameField.clear();
				}
			} else {
				
			}
		});
		
		searchBtn.setOnMouseEntered(e -> {
			searchBtn.setBackground(darkMode3);
		});
		
		searchBtn.setOnMouseExited(e -> {
			searchBtn.setBackground(darkMode);
		});
		
		HBox input = new HBox(20);
		input.setAlignment(Pos.CENTER);
		input.getChildren().addAll(usernameField);
		
		VBox vbox = new VBox(50);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(input, searchBtn);
		
		followPane = new HBox(100);
		followPane.setAlignment(Pos.CENTER);
		followPane.getChildren().addAll(vbox);
	}
	
	public HBox getFollowPane() {
		return followPane;
	}
	
}
