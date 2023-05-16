package view;

import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.AccountTree;
import model.Comment;
import model.Post;
import util.Utilities;

import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.LinkedList;

public class AccountView {
	private HBox accountPane;
	Background darkMode = new Background(new BackgroundFill(Color.rgb(70, 70, 70), null, null));
	Background darkMode3 = new Background(new BackgroundFill(Color.rgb(80, 80, 80), null, null));
	Border darkMode2 = new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, new BorderWidths(1)));
	Border darkMode4 = new Border(new BorderStroke(Color.rgb(50, 50, 50), BorderStrokeStyle.SOLID, null, new BorderWidths(40)));
	String whiteFont = "-fx-text-fill: white;";
	String dark = "-fx-background-color: #000000;";
	String darkPost = "-fx-control-inner-background:#404040;-fx-background-color:#404040;-fx-text-fill: white;";
	
	public AccountView(AccountTree accountTree) {
		VBox posts = new VBox(20);
		posts.setAlignment(Pos.CENTER);
		posts.setBackground(darkMode);
	
		TextArea accountInfo = new TextArea();
		accountInfo.setEditable(false);
		accountInfo.setWrapText(true);
		accountInfo.setText("Username: @" + Utilities.getActiveAccount().getUsername()
				+ "\nPassword: " + Utilities.getActiveAccount().getPassword()
				+ "\n\nFollowing:\n" + Utilities.getActiveAccount().getFollowedAccounts());
		accountInfo.setPrefSize(200, 300);
		accountInfo.setStyle(darkPost);
		
		LinkedList<Post> postsList = new LinkedList<>();
		postsList.addAll(Utilities.getActiveAccount().getPosts());

		Iterator<Post> iterator = postsList.descendingIterator();

		while (iterator.hasNext()) {
		    Post post = iterator.next();
			VBox content = new VBox();

	        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
	        
	        TextArea userPost = new TextArea();
	        userPost.setWrapText(true);
	        userPost.setEditable(false);
	        userPost.setText("@" + post.getPoster().getUsername() 
	        		+ "\n" + dtf.format(post.getDate())
	        		+ "\n\n" + post.getHeader()
	        		+ "\n\n" + post.getBody());
	        userPost.setMaxSize(800, 150);
	        userPost.setStyle(darkPost);
	        userPost.setBorder(darkMode2);
	        
	        VBox commentsBox = new VBox(5);
	        commentsBox.setBackground(darkMode);
	        commentsBox.setAlignment(Pos.CENTER);
	        
	        for (Comment pComment: post.getComments()) {
	        	TextArea postComment = new TextArea();
	        	postComment.setWrapText(true);
	        	postComment.setEditable(false);
	        	postComment.setText("@" + pComment.getPoster().getUsername()
	        			+ "\n" + dtf.format(post.getDate())
	        			+ "\n\n" + pComment.getBody());
	        	postComment.setMaxSize(350, 150);
	        	postComment.setStyle(darkPost);
	        	postComment.setBorder(darkMode2);
	        	commentsBox.getChildren().add(postComment);
	        }
	        
	        content.getChildren().addAll(userPost, commentsBox);

	        posts.getChildren().add(content);
	        System.out.println("Added Post");
		}
		
		ScrollPane scrollPosts = new ScrollPane(posts);
		scrollPosts.setMinSize(500, 300);
		scrollPosts.setPrefSize(500, 300);

		VBox vbox = new VBox(30);
		vbox.setAlignment(Pos.CENTER);
		vbox.setBorder(darkMode4);
		vbox.getChildren().addAll(accountInfo, scrollPosts);

		accountPane = new HBox(100);
		accountPane.setAlignment(Pos.CENTER);
		accountPane.getChildren().addAll(vbox);

	}

	public HBox getAccountPane() {
		return accountPane;
	}
}
