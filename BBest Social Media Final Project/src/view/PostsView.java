package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
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
import model.Account;
import model.AccountTree;
import model.Comment;
import model.Post;
import util.Backup;
import util.Utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.LinkedList;

public class PostsView {
	private HBox postsPane;
	Background darkMode = new Background(new BackgroundFill(Color.rgb(70, 70, 70), null, null));
	Background darkMode3 = new Background(new BackgroundFill(Color.rgb(80, 80, 80), null, null));
	Border darkMode2 = new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, new BorderWidths(1)));
	Border darkMode4 = new Border(new BorderStroke(Color.rgb(50, 50, 50), BorderStrokeStyle.SOLID, null, new BorderWidths(20)));
	String whiteFont = "-fx-text-fill: white;-fx-prompt-text-fill:#808080;";
	String darkPost = "-fx-control-inner-background:#404040;-fx-background-color:#404040;-fx-text-fill: white;";
	
	public PostsView(AccountTree accountTree) {
		
		Button postBtn = new Button("Post");
		postBtn.setPrefSize(150, 50);
		postBtn.setBackground(darkMode);
		postBtn.setTextFill(Color.WHITE);
		postBtn.setBorder(darkMode2);
		
		TextField headerField = new TextField();
		headerField.setPromptText("Post Header");
		headerField.setMaxSize(150, 30);
		headerField.setBackground(darkMode);
		headerField.setStyle(whiteFont);
		headerField.setBorder(darkMode2);
		
		TextField bodyField = new TextField();
		bodyField.setPromptText("Post Body");
		bodyField.setMaxSize(150, 30);
		bodyField.setBackground(darkMode);
		bodyField.setStyle(whiteFont);
		bodyField.setBorder(darkMode2);
		
		TextArea console = new TextArea();
		console.setWrapText(true);
		console.setEditable(false);
		console.setText("Console:");
		console.setPrefSize(300, 950);
		console.setBackground(darkMode);
		console.setStyle(darkPost);
		console.setBorder(darkMode2);
		
		Button clearBtn = new Button("Clear Console");
		clearBtn.setPrefSize(100, 50);
		clearBtn.setBackground(darkMode);
		clearBtn.setTextFill(Color.WHITE);
		clearBtn.setBorder(darkMode2);
		
		Button postAnyways = new Button("Post W/O check");
		postAnyways.setPrefSize(150, 50);
		postAnyways.setBackground(darkMode);
		postAnyways.setTextFill(Color.WHITE);
		postAnyways.setBorder(darkMode2);
		
		postBtn.setOnAction(e -> {
			if (headerField.getText() != "" && bodyField.getText() != "") {
				String header = headerField.getText();
				String body = bodyField.getText();
        		LinkedList<String> misspelledWords = new LinkedList<String>();
        		misspelledWords = Utilities.spellCheck(header + " " + body);
				if (misspelledWords.isEmpty() || misspelledWords.toString().equals("[]")) {
					LocalDateTime time = LocalDateTime.now();
					Post post = new Post(Utilities.getActiveAccount(), time, header, body);
					Utilities.getActiveAccount().addPost(post);
					console.appendText("\n\t Post added, go to your account to view it");
					Backup.save("backupFolder/AccountTree.dat", accountTree);
					headerField.clear();
					bodyField.clear();
				} else {
					console.appendText("\n\t Spelling errors exist in your post"
        					+ "\nThose being: " + misspelledWords.toString());
				}
			}
		});
		
		postAnyways.setOnAction(e -> {
			if (headerField.getText() != "" && bodyField.getText() != "") {
				String header = headerField.getText();
				String body = bodyField.getText();
				LocalDateTime time = LocalDateTime.now();
				Post post = new Post(Utilities.getActiveAccount(), time, header, body);
				Utilities.getActiveAccount().addPost(post);
				console.appendText("\n\t Post added, go to your account to view it");
				Backup.save("backupFolder/AccountTree.dat", accountTree);
				headerField.clear();
				bodyField.clear();
			}
		});
		
		postBtn.setOnMouseEntered(e -> {
			postBtn.setBackground(darkMode3);
		});
		
		postBtn.setOnMouseExited(e -> {
			postBtn.setBackground(darkMode);
		});
		
		clearBtn.setOnAction(e -> {
			console.setText("Console:");
		});
		
		clearBtn.setOnMouseEntered(e -> {
			clearBtn.setBackground(darkMode3);
		});
		
		clearBtn.setOnMouseExited(e -> {
			clearBtn.setBackground(darkMode);
		});
		
		HBox btns = new HBox(20);
		btns.setAlignment(Pos.CENTER);
		btns.getChildren().addAll(postBtn, postAnyways);
		
		HBox input = new HBox(20);
		input.setAlignment(Pos.CENTER);
		input.getChildren().addAll(headerField, bodyField);
		
		VBox postBox = new VBox(30);
		postBox.setAlignment(Pos.CENTER);
		postBox.getChildren().addAll(input, btns);
		
		VBox posts = new VBox(20);
		posts.setBackground(darkMode);
		posts.setAlignment(Pos.CENTER);

		LinkedList<Post> postsList = new LinkedList<>();
		LinkedList<Account> following = Utilities.getActiveAccount().getFollowing();
		for (Account account : following) {
		    postsList.addAll(account.getPosts());
		}

		Collections.sort(postsList, (a, b) -> b.getDate().compareTo(a.getDate()));
		
		for (Post post : postsList){
	        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
	        
	        TextField comment = new TextField();
	        comment.setPromptText("comment");
	        comment.setMaxSize(300, 30);
	        comment.setBackground(darkMode);
	        comment.setStyle(whiteFont);
	        comment.setBorder(darkMode2);
	        
	        Button commentBtn = new Button("Add Comment To Thread");
	        commentBtn.setMaxSize(300, 30);
	        commentBtn.setBackground(darkMode);
	        commentBtn.setTextFill(Color.WHITE);
	        commentBtn.setBorder(darkMode2);
	        
	        Button commentAnyways = new Button("Add Comment W/O Check");
	        commentAnyways.setMaxSize(300, 30);
	        commentAnyways.setBackground(darkMode);
	        commentAnyways.setTextFill(Color.WHITE);
	        commentAnyways.setBorder(darkMode2);
	        
	        commentBtn.setOnAction( e -> {
	        	if (comment.getText() != "") {
	        		String commentText = comment.getText();
	        		LinkedList<String> misspelledWords = new LinkedList<String>();
	        		misspelledWords = Utilities.spellCheck(commentText);
	        		if (misspelledWords.isEmpty() || misspelledWords.toString().equals("[]")) {
	        			LocalDateTime time = LocalDateTime.now();
	        			Comment commentPost = new Comment(Utilities.getActiveAccount(), time, commentText);
	        			post.addComment(commentPost);
	        			console.appendText("\n\t Comment added, refresh page to view it");
	        			Backup.save("backupFolder/AccountTree.dat", accountTree);
	        			comment.clear();
	        		} else {
	        			console.appendText("\n\t Spelling errors exist in your comment"
	        					+ "\nThose being: " + misspelledWords.toString());
	        		}
				}
	        });
	        
	        commentAnyways.setOnAction( e -> {
	        	if (comment.getText() != "") {
	        		String commentText = comment.getText();
	        		LocalDateTime time = LocalDateTime.now();
	        		Comment commentPost = new Comment(Utilities.getActiveAccount(), time, commentText);
	        		post.addComment(commentPost);
	        		console.appendText("\n\t Comment added, refresh page to view it");
	        		Backup.save("backupFolder/AccountTree.dat", accountTree);
	        		comment.clear();
				}
	        });
	        
	        VBox commentBtns = new VBox(5);
	        commentBtns.setBackground(darkMode);
	        commentBtns.setAlignment(Pos.CENTER);
	        commentBtns.getChildren().addAll(commentBtn, commentAnyways);
	        
	        HBox commentBox = new HBox(5);
	        commentBox.setAlignment(Pos.CENTER);
	        commentBox.getChildren().addAll(comment, commentBtns);
	        
	        TextArea postUser = new TextArea();
	        postUser.setWrapText(true);
	        postUser.setEditable(false);
	        postUser.setText("@" + post.getPoster().getUsername() 
	        		+ "\n" + dtf.format(post.getDate())
	        		+ "\n\n" + post.getHeader()
	        		+ "\n\n" + post.getBody());
	        postUser.setMaxSize(540, 150);
	        postUser.setStyle(darkPost);
	        postUser.setBorder(darkMode2);
	        
	        VBox commentsBox = new VBox(5);
	        commentsBox.setBackground(darkMode);
	        commentsBox.setAlignment(Pos.CENTER);
	        
	        for (Comment pComment: post.getComments()) {
	        	TextArea postComment = new TextArea();
	        	postComment.setWrapText(true);
	        	postComment.setEditable(false);
	        	postComment.setText("@" + pComment.getPoster().getUsername()
	        			+ "\n" + dtf.format(pComment.getDate())
	        			+ "\n\n" + pComment.getBody());
	        	postComment.setMaxSize(350, 150);
	        	postComment.setStyle(darkPost);
	        	postComment.setBorder(darkMode2);
	        	commentsBox.getChildren().add(postComment);
	        }
	        
	        posts.setBorder(darkMode2);
	        posts.getChildren().addAll(postUser, commentBox, commentsBox);
	        System.out.println("Added Post");
		}
		posts.setMaxWidth(540);
		
		ScrollPane scrollPosts = new ScrollPane(posts);
		scrollPosts.setStyle(darkPost);
		scrollPosts.setPrefWidth(550);
		
		VBox vbox = new VBox(30);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(postBox, scrollPosts);
		
		HBox consoleBtns = new HBox();
		consoleBtns.setAlignment(Pos.CENTER);
		consoleBtns.getChildren().addAll(clearBtn);
		
		VBox consoleBox = new VBox(20);
		consoleBox.setAlignment(Pos.CENTER);
		consoleBox.getChildren().addAll(console, consoleBtns);
		
		HBox hbox = new HBox(20);
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(vbox, consoleBox);
		hbox.setBorder(darkMode4);
		
		postsPane = new HBox(100);
		postsPane.setAlignment(Pos.CENTER);
		postsPane.getChildren().addAll(hbox);
		
	}
	
	public HBox getPostsPane() {
		return postsPane;
	}
}
