package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class WelcomeView {
	private HBox welcomePane;
	Background darkMode = new Background(new BackgroundFill(Color.rgb(70, 70, 70), null, null));
	Background darkMode3 = new Background(new BackgroundFill(Color.rgb(80, 80, 80), null, null));
	String whiteFont = "-fx-text-fill: white;";
	
	public WelcomeView() {
		Button welcomeBtn = new Button("Welcome to Spamton Net!");
		welcomeBtn.setPrefSize(400, 200);
		welcomeBtn.setFont(new Font(20));
		welcomeBtn.setBackground(darkMode);
		welcomeBtn.setTextFill(Color.WHITE);
		ImageView imageView = null;
		
		try {
			imageView = new ImageView(new Image(new FileInputStream("dataFolder/spamton.gif")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		imageView.setFitWidth(200);
		imageView.setFitHeight(200);
		
		welcomePane = new HBox(50);
		welcomePane.setAlignment(Pos.CENTER);
		welcomePane.getChildren().addAll(welcomeBtn, imageView);
	}
	
	public HBox getWelcomePane() {
		return welcomePane;
	}
	
}
