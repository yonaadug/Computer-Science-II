package assignment1;

import javafx.scene.layout.BorderPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

/**
 * Gui for the Password Checker application
 * @author Yonathan Kebede 
 * 
 */
public class FXMainPane extends BorderPane {

	private Button checkPass, checkPassFile, exit;
	private TextField passTextField, retypeTextField;
	private Label passRequirements, passLabel, retypeLabel;		
	
	public FXMainPane() {
		
		//Alert
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Password Status");
		alert.setHeaderText(null);
		
		//Create Buttons
		checkPass = new Button("Check Password");
		checkPassFile = new Button("Check passwords in File");
		exit = new Button("Exit");
			
		//Textfields
		passTextField = new TextField();
		retypeTextField = new TextField();
		
		//Labels
		passLabel = new Label("Password");
		retypeLabel = new Label("Re-type\npassword");
		passRequirements = new Label(
				"Use the following rules when creating your password:"+
						"\n\t 1. Length must be greater than 6; a strong password will contain at least 10 characters"+
						"\n\t 2. Must contain at least one upper case alpha character"+
						"\n\t 3. Must contain at least one lower case alpha character"+
						"\n\t 4. Must contain at least one numeric charcter"+
						"\n\t 5. May not have more than 2 of the same character in sequence"
				);
		
		//create the description
	    VBox descriptionBox = new VBox(10);
	    descriptionBox.getChildren().addAll(passRequirements); 
	    setTop(descriptionBox);
	    descriptionBox.setPadding(new Insets(20));
	    
	    //Make Buttons
		checkPass.setMnemonicParsing(true);  
		checkPassFile.setMnemonicParsing(true);
		exit.setMnemonicParsing(true);
		
	    HBox.setMargin(checkPass, new Insets(5,10,10,10));
	    HBox.setMargin(checkPassFile,new Insets(5,10,10,10));
	    HBox.setMargin(exit,new Insets(5,10,10,10));
	    
	    HBox buttons = new HBox();
	    buttons.getChildren().addAll(checkPass,checkPassFile,exit);
	    setBottom(buttons);
	    buttons.setAlignment(Pos.CENTER);
	    
	    
	    //Password section
	    VBox.setMargin(passLabel, new Insets(40,10,10,10));
	    VBox.setMargin(retypeLabel, new Insets(40,10,10,10));
	    VBox.setMargin(passTextField, new Insets(35,10,5,10) );
	    VBox.setMargin(retypeTextField, new Insets(45,10,5,10));
	    VBox labelBox = new VBox();
	    VBox textBox = new VBox();
	    HBox centerBox = new HBox();
	    labelBox.getChildren().addAll(passLabel,retypeLabel);
	    textBox.getChildren().addAll(passTextField,retypeTextField);
	    centerBox.getChildren().addAll(labelBox,textBox);
	    centerBox.setAlignment(Pos.CENTER);
	    setCenter(centerBox);
	    
	    
	    //Check password file button action
	    checkPassFile.setOnAction( event -> {
	    	try {
	    		
	    		ArrayList<String> passwordList = new ArrayList<String>();
	    		FileChooser fileChooser = new FileChooser();
	    		File selectedFile = fileChooser.showOpenDialog(null);
	    		
	    		if(selectedFile != null) {
	    			Scanner scan = new Scanner(selectedFile);
	    			
	    			while(scan.hasNextLine()) {
	    				passwordList.add(scan.nextLine());
	    				
	    			}
	    			//Give passwordList the address of incorrect passwords with their
	    			//error messages
	    			passwordList = PasswordCheckerUtility.validPasswords(passwordList);
	    			String incorrectPass = ""; 
	    			alert.setHeaderText("Illegal Passwords");
	    			
	    			for(String str: passwordList) {
	    				incorrectPass += "\n" + str;
	    			}
	    			
	    			alert.setContentText(incorrectPass);
	    			alert.showAndWait();
	    			scan.close();
	    		}
	    	}catch(FileNotFoundException e) {
	    		alert.setContentText("File not found");
				alert.showAndWait();
	    	}catch(Exception e) {
	    		alert.setContentText(e.getMessage());
				alert.showAndWait();	    		
	    	}
	    });
	    
	    //Check password button action
	    checkPass.setOnAction( event -> {
	    	
	    	try {
	    		//Check if passwords match first
	    		if(passTextField.getText().equals(retypeTextField.getText())) {
	    			
	    			PasswordCheckerUtility.isValidPassword(passTextField.getText());
	    			if(PasswordCheckerUtility.isWeakPassword(passTextField.getText())) {
	    				//If password is weak
	    				alert.setContentText("Password is OK but weak");
	    				alert.showAndWait();
	    			}else {
	    				//If password is not weak
	    				alert.setContentText("Password is valid");
	    				alert.showAndWait();
	    			}
	    			
	    			
	    		}else {
	    			
	    			throw new UnmatchedException("The passwords do not match");
	    		}
	    		
	    	}catch(Exception e) {
	    		//Error notifications
	    		alert.setContentText( e.getMessage());
	    		alert.showAndWait();
	    		
	    	}
	    	
	    	
	    });
	    
	    //Exit button action
	    exit.setOnAction(
        		event -> {
            	 Platform.exit();
                 System.exit(0);
        		}
        	);
		
	}	
	
	
}
