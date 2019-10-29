package app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import app.Poker;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import javafx.animation.Timeline;

public class ClientStartController implements Initializable {

	@FXML
	private TextField txtPlayerName;
	@FXML
	private TextField txtClientPort;
	@FXML
	private TextField txtComputerName;

	private Poker mainApp;

    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		// This is the crazy way you have to code to make an item
		// the default selection / focus
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				txtPlayerName.requestFocus();
			}
		});
	}

	public void setMainApp(Poker mainApp) {
		this.mainApp = mainApp;
	}


	@FXML
	public void btnOK(ActionEvent event) {
 
		boolean bServer = false;
		String strComputerName = txtComputerName.getText();
		int iPort = Integer.parseInt(txtClientPort.getText());
		mainApp.StartClient(strComputerName, iPort, txtPlayerName.getText());
	}

	@FXML
	public void btnCancel(ActionEvent event) {
		Platform.exit();
		System.exit(0);

		System.out.println("End Program");

	}

}