package app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import app.Poker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import pkgCore.Action;
import pkgCore.Table;
import pkgEnum.eAction;

public class TexasHoldemController implements Initializable {

	@FXML
	private Label p4Label;
	
	private Poker mainApp;
	
	public void setMainApp(Poker mainApp) {
		this.mainApp = mainApp;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	private void btnSit(ActionEvent event) {
	
		Button btnSit = (Button)event.getSource();
		this.mainApp.getAppPlayer()
		.setiPlayerPosition(Integer.parseInt(btnSit.getId()
				.substring(btnSit.getId().length()-1)));

		Action act = new Action(eAction.Sit, this.mainApp.getAppPlayer());
		
		this.mainApp.messageSend(act);
		
	}
	
	public void HandleTableState(Table currentTable)
	{
		p4Label.setText(currentTable.getTablePlayers().get(0).getPlayerName());
	}
}
