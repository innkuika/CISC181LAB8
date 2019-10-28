package app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import app.ServerStart;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class ServerStartController implements Initializable {

	@FXML
	private TextField txtServerPort;
	@FXML
	private TextField txtComputerName;

	private ServerStart mainApp;

	/**
	 * @author BRG
	 * @version Lab #6
	 * @since Lab #6
	 * 
	 *        initialize - Run this before any controller method
	 * 
	 * @author BRG
	 *
	 */

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// This is the crazy way you have to code to make an item
		// the default selection / focus

	}

	public void setMainApp(ServerStart mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * @author BRG
	 * @version Lab #6
	 * @since Lab #6
	 * 
	 *        btnOK - Call the StartServer method in main application
	 * 
	 * @author BRG
	 *
	 */

	@FXML
	public void btnOK(ActionEvent event) {
		int iPort = 0;
		String strComputerName = "localhost";
		boolean bServer = true;
		iPort = Integer.parseInt(txtServerPort.getText());
		mainApp.StartServer(bServer, strComputerName, iPort);
	}

	@FXML
	public void btnCancel(ActionEvent event) {
		
		try {
			this.mainApp.stop();
		} catch (Exception e) {
		}

		Platform.exit();
		System.exit(0);
		System.out.println("End Program");
	}

}