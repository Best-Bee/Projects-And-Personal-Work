//*****************************************
//	Brendan Best
//	Updated As Of: 12/14/2022
//	Social Media App Project
//	
//*****************************************

package app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.AccountTree;
import util.Restore;
import util.Utilities;
import view.AccountView;
import view.FollowView;
import view.LoginView;
import view.PostsView;
import view.WelcomeView;

public class Demo extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		AccountTree accountTree = new AccountTree();
		Restore.load("backupFolder/AccountTree.dat", accountTree);
		
		LoginView loginView = new LoginView(accountTree);
		WelcomeView welcomeView = new WelcomeView();
		FollowView followView = new FollowView(accountTree);
		
		Background darkMode = new Background(new BackgroundFill(Color.rgb(50, 50, 50), null, null));
		Background darkMode2 = new Background(new BackgroundFill(Color.rgb(120, 120, 120), null, null));
		Border darkMode3 = new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, new BorderWidths(1)));
		
		BorderPane root = new BorderPane();
		
		HBox menuBar = new HBox(10);
		
		Button welcomeBtn = new Button("Home");
		welcomeBtn.setPrefSize(100, 30);
		welcomeBtn.setBackground(darkMode);
		welcomeBtn.setTextFill(Color.WHITE);
		welcomeBtn.setBorder(darkMode3);
		
		Button loginBtn = new Button("Login");
		loginBtn.setPrefSize(100, 30);
		loginBtn.setBackground(darkMode);
		loginBtn.setTextFill(Color.WHITE);
		loginBtn.setBorder(darkMode3);
		
		Button postsBtn = new Button("Posts");
		postsBtn.setPrefSize(100, 30);
		postsBtn.setBackground(darkMode);
		postsBtn.setTextFill(Color.WHITE);
		postsBtn.setBorder(darkMode3);
		
		Button accountBtn = new Button("Account");
		accountBtn.setPrefSize(100, 30);
		accountBtn.setBackground(darkMode);
		accountBtn.setTextFill(Color.WHITE);
		accountBtn.setBorder(darkMode3);
		
		Button followBtn = new Button("Follow");
		followBtn.setPrefSize(100, 30);
		followBtn.setBackground(darkMode);
		followBtn.setTextFill(Color.WHITE);
		followBtn.setBorder(darkMode3);
		
		Button exitBtn = new Button("Exit");
		exitBtn.setPrefSize(100, 30);
		exitBtn.setBackground(darkMode);
		exitBtn.setTextFill(Color.WHITE);
		exitBtn.setBorder(darkMode3);
		
		loginBtn.setOnAction(e -> {
			root.setCenter(loginView.getLoginPane());
		});
		
		welcomeBtn.setOnAction(e -> {
			root.setCenter(welcomeView.getWelcomePane());
		});
		
		postsBtn.setOnAction(e -> {
			PostsView postsView = new PostsView(accountTree);
			if (Utilities.getActiveAccount().getUsername() != null) {
				root.setCenter(postsView.getPostsPane());
			} else {
				root.setCenter(loginView.getLoginPane());
			}
		});
		
		accountBtn.setOnAction(e -> {
			AccountView accountView = new AccountView(accountTree);
			if (Utilities.getActiveAccount().getUsername() != null) {
				root.setCenter(accountView.getAccountPane());
			} else {
				root.setCenter(loginView.getLoginPane());
			}
		});
		
		followBtn.setOnAction(e -> {
			if (Utilities.getActiveAccount().getUsername() != null) {
				root.setCenter(followView.getFollowPane());
			} else {
				root.setCenter(loginView.getLoginPane());
			}
		});
		
		exitBtn.setOnAction(e -> {
			Platform.exit();
		});
		
		menuBar.getChildren().addAll(welcomeBtn, loginBtn, accountBtn, followBtn, postsBtn, exitBtn);
		
		menuBar.setBackground(darkMode2);
		menuBar.setBorder(darkMode3);
		
		root.setTop(menuBar);
		root.setCenter(welcomeView.getWelcomePane());
		root.setBackground(darkMode);
		
		Scene scene = new Scene(root, 800, 600);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}