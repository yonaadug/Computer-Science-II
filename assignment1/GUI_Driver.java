package assignment1;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;


/**
 * Driver for the Password Checker GUI in JavaFX
 * @author Yonathan Kebede
 *
 */
public class GUI_Driver extends Application {

	
	public void start(Stage stage) {
		
		
		FXMainPane root = new FXMainPane();
		stage.setScene(new Scene(root, 650, 450));
		// Set stage title and show the stage.
		stage.setTitle("Password Checker");
		stage.show();
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	
}
