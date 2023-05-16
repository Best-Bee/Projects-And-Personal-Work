package app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.PersonBag;
import model.TextbookBag;
import util.Restore;
import util.Utilities;
import util.Backup;
import view.StudentView;
import view.TextbookView;
import view.HomeView;
import view.InstructorView;

public class Demo extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		PersonBag personBag = Restore.restorePersonBag();
		TextbookBag textbookBag = Restore.restoreTextbookBag();
		//PersonBag personBag = Utilities.loadPerson(Utilities.importStudents(), Utilities.importInstructors());
		//TextbookBag textbookBag = Utilities.loadTextbook(Utilities.importBooks());
		//Backup.backupPersonBag(personBag);
		//Backup.backupTextbookBag(textbookBag);
		
		HomeView homeView = new HomeView();
		
		Background darkMode = new Background(new BackgroundFill(Color.rgb(50, 50, 50), null, null));
		Background darkMode2 = new Background(new BackgroundFill(Color.rgb(120, 120, 120), null, null));
		
		BorderPane root = new BorderPane();
		MenuBar menuBar = new MenuBar();
		
		Menu fileMenu = new Menu("File");
		Menu editMenu = new Menu("Edit");
		Menu exitMenu = new Menu("App");
		
		MenuItem importBooksItem = new MenuItem("Import Books");
		MenuItem importPersonsItem = new MenuItem("Import Persons");
		MenuItem home = new MenuItem("Home");
		
		MenuItem studentMenu = new MenuItem("Student");
		MenuItem instructorMenu = new MenuItem("Instructor");
		MenuItem textbookMenu = new MenuItem("Textbook");
		
		MenuItem exitItem = new MenuItem("Exit");
		
		editMenu.getItems().addAll(studentMenu, instructorMenu, textbookMenu);
		
		exitMenu.getItems().addAll(exitItem);
		
		exitItem.setOnAction(e -> {
			Platform.exit();
		});
		importBooksItem.setOnAction(e -> {
			Utilities.loadTextbook(Utilities.importBooks());
			System.out.println("Loaded Books");
		});
		importPersonsItem.setOnAction(e -> {
			Utilities.loadPerson(Utilities.importStudents(), Utilities.importInstructors());
			System.out.println("Loaded People");
		});
		
		home.setOnAction(e -> {
			root.setCenter(homeView.getHomePane());
			primaryStage.setTitle("Home");
		});
		
		StudentView studentView = new StudentView(personBag);
		studentMenu.setOnAction(e -> {
			root.setCenter(studentView.getStudentPane());
			primaryStage.setTitle("Edit Student");
		});
		TextbookView textbookView = new TextbookView(textbookBag);
		textbookMenu.setOnAction(e -> {
			root.setCenter(textbookView.getTextbookPane());
			primaryStage.setTitle("Edit Textbook");
		});
		InstructorView instructorView = new InstructorView(personBag);
		instructorMenu.setOnAction(e -> {
			root.setCenter(instructorView.getInstructorPane());
			primaryStage.setTitle("Edit Instructor");
		});
		
		fileMenu.getItems().addAll(home, importBooksItem, importPersonsItem);
		
		menuBar.getMenus().addAll(fileMenu, editMenu, exitMenu);
		
		menuBar.setBackground(darkMode2);
		
		root.setTop(menuBar);
		root.setCenter(homeView.getHomePane());
		root.setBackground(darkMode);
		Scene scene = new Scene(root, 700, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}