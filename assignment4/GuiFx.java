package assignment4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class GuiFx extends Application {

	// input and output files
	File selectedInputFile, selectedOutputFile;

	// array of lagels
	String[] selectionLabels = { "Create Concordance from File", "Create Concordance from Text" };

	// array of RadioButton
	RadioButton[] radioButtons;

	// Buttons
	Button createConcordanceBtn, selectInputButton, selectOutputButton, clearButton, exitButton;
	// textbox
	TextArea displayTextArea;

	ConcordanceDataManager mgr = new ConcordanceDataManager(); // Instance of the data manager class

	
	public void start(Stage stage) {

		createConcordanceBtn = new Button("Create Concordance");
		selectInputButton = new Button("Select Input File");
		selectOutputButton = new Button("Select Output File");
		clearButton = new Button("Clear");
		exitButton = new Button("Exit");

		createConcordanceBtn.setOnAction(e -> {
			String selectedPosition = null;

		

			for (RadioButton rb : radioButtons) {
				if (rb.isSelected()) {
					selectedPosition = rb.getText();
				}
			}

			if (selectedPosition.equals("Create Concordance from File")) {
				try {

					mgr.createConcordanceFile(selectedInputFile, selectedOutputFile);
				} catch (FileNotFoundException err) {
					err.printStackTrace();
				}
			}

		
			else if (selectedPosition.equals("Create Concordance from Text")) {
				ArrayList<String> data = new ArrayList<String>();
				String textData = displayTextArea.getText();
				String concordanceText = "";

				data = mgr.createConcordanceArray(textData);

				for (int i = 0; i < data.size(); i++) {
					concordanceText += data.get(i);
				}

				displayTextArea.setText(concordanceText);

			}
		});
		selectInputButton.setOnAction(e ->{
			JFileChooser fileChooser = new JFileChooser();
			int status = fileChooser.showOpenDialog(null);

			if (status == JFileChooser.APPROVE_OPTION) {
				selectedInputFile = fileChooser.getSelectedFile();
			}

		
			if (selectedOutputFile != null) {
				createConcordanceBtn.setDisable(false);
			}
		});
		selectOutputButton.setOnAction(e ->{	
			
			JFileChooser fileChooser = new JFileChooser();
			int status = fileChooser.showOpenDialog(null);

			if (status == JFileChooser.APPROVE_OPTION) {
				selectedOutputFile = fileChooser.getSelectedFile();
			}

		
			if (selectedInputFile != null) {
				createConcordanceBtn.setDisable(false);
			}});
		clearButton.setOnAction(e ->{ {
			displayTextArea.clear();
		}});
		exitButton.setOnAction(e ->{	System.exit(0);});

	
		displayTextArea = new TextArea();
		displayTextArea.setPrefWidth(650);
		displayTextArea.setPrefHeight(250);
		displayTextArea.setPadding(new Insets(10, 10, 10, 10));
		displayTextArea.setDisable(true);

		HBox concordanceRadioBox = new HBox(15);
		concordanceRadioBox.setAlignment(Pos.CENTER_LEFT);
		ToggleGroup radiosGroup = new ToggleGroup();
		radioButtons = new RadioButton[selectionLabels.length];

		for (int i = 0; i < radioButtons.length; i++) {
			radioButtons[i] = new RadioButton(selectionLabels[i]);
			radioButtons[i].setToggleGroup(radiosGroup);

			radioButtons[i].setOnAction(e -> ButtonClicked(e));
		}
		concordanceRadioBox.getChildren().addAll(radioButtons);

		TitledPane radioBoxPane = new TitledPane("Please Select from Following Options:", concordanceRadioBox);
		radioBoxPane.setCollapsible(false);

		TitledPane displayTextPane = new TitledPane("Enter Text:", displayTextArea);
		displayTextPane.setCollapsible(false);

		StackPane stack = new StackPane();
		stack.getChildren().addAll(displayTextPane);

		HBox buttonPane = new HBox();
		buttonPane.setAlignment(Pos.CENTER);
		buttonPane.setPadding(new Insets(0, 5, 0, 5));
		buttonPane.getChildren().addAll(createConcordanceBtn, selectInputButton, selectOutputButton, clearButton,
				exitButton);
	
		VBox contentPane = new VBox();
		contentPane.setAlignment(Pos.CENTER);
		contentPane.getChildren().addAll(radioBoxPane, displayTextPane, buttonPane);

		BorderPane displayPane = new BorderPane();
		displayPane.setCenter(contentPane);

		Scene scene = new Scene(displayPane);
		stage.setTitle("Concordance Generator");
		stage.setScene(scene);
		stage.show();
	}

	
	public void ButtonClicked(ActionEvent e) {
	
		if (e.getSource() == radioButtons[0]) {
			displayTextArea.setVisible(false);
			selectInputButton.setDisable(false);
			selectOutputButton.setDisable(false);
			displayTextArea.setDisable(true);

			createConcordanceBtn.setDisable(true);
		}

		else if (e.getSource() == radioButtons[1]) {
			selectInputButton.setDisable(true);
			selectOutputButton.setDisable(true);
			displayTextArea.setVisible(true);
			createConcordanceBtn.setDisable(false);
			displayTextArea.setDisable(false);
		}
	}




	public static void main(String[] args) {
		launch(args);
	}
}