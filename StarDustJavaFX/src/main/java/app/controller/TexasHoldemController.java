package app.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import app.Poker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.Node;
import pkgCore.Action;
import pkgCore.Player;
import pkgCore.Table;
import pkgEnum.eAction;

public class TexasHoldemController implements Initializable {

	@FXML
	private BorderPane parentNode;

	@FXML
	private Label PlayerLabel1;
	@FXML
	private Label PlayerLabel2;
	@FXML
	private Label PlayerLabel3;
	@FXML
	private Label PlayerLabel4;
	@FXML
	private Label PlayerLabel5;
	@FXML
	private Label PlayerLabel6;
	@FXML
	private Label PlayerLabel7;
	@FXML
	private Label PlayerLabel8;
	@FXML
	private Label PlayerLabel9;

	@FXML
	private HBox p1HBoxCards;
	@FXML
	private HBox p2HBoxCards;
	@FXML
	private HBox p3HBoxCards;
	@FXML
	private HBox p4HBoxCards;
	@FXML
	private HBox p5HBoxCards;
	@FXML
	private HBox p6HBoxCards;
	@FXML
	private HBox p7HBoxCards;
	@FXML
	private HBox p8HBoxCards;
	@FXML
	private HBox p9HBoxCards;

	private Poker mainApp;

	public void setMainApp(Poker mainApp) {
		this.mainApp = mainApp;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	/**
	 * HandleTableState - Run this method if the Table has changed.
	 * 
	 * @author BRG
	 * @version Lab #6
	 * @since Lab #6
	 * 
	 * @param currentTable
	 */
	public void HandleTableState(Table currentTable) {

		if ((currentTable == null) || (currentTable.getTablePlayers() == null))
			return;

		// Blank out all the player labels
		for (Node n : getAllControls(parentNode, new Label())) {
			Label l = (Label) n;
			if ((l.getId() != null) && (l.getId().contains("PlayerLabel"))) {
				l.setText("");
			}
		}

		// Update the Player Label
		for (Player p : currentTable.getTablePlayers()) {
			String strLabel = "PlayerLabel" + p.getiPlayerPosition();
			SetLabelText(strLabel, p.getPlayerName());
		}

		// Am I sitting? If I am, the other buttons should be not visible
		// Am I sitting? If I am, the seated position should be 'Leave'
		// Am I not sitting? Every other button where someone is not sitting should be
		// 'Sit'.

		// iPlayerSittingCount - this will be 1 if the player is seated

		Optional<Player> SeatedAppPlayer = currentTable.getTablePlayers().stream()
				.filter(x -> x.getPlayerID().equals(this.mainApp.getAppPlayer().getPlayerID())).findFirst();

		for (Node n : getAllNodes(parentNode)) {
			if (n instanceof Button) {
				Button btnSit = (Button) n;
				if ((n.getId() != null) && (n.getId().contains("btnSit"))) {
					Optional<Player> pSeated = currentTable.getTablePlayers().stream()
							.filter(x -> x.getiPlayerPosition() == Integer
									.parseInt(btnSit.getId().substring(btnSit.getId().length() - 1)))
							.findFirst();

					// I'm not seated anywhere, no one is seated in this position
					if ((pSeated.isEmpty()) && (SeatedAppPlayer.isEmpty())) {
						btnSit.setText("Sit");
						btnSit.setVisible(true);
						btnSit.setDisable(false);
						// Someone is sitting at position, I'm sitting, It's me
					} else if ((pSeated.isPresent()) && (SeatedAppPlayer.isPresent()
							&& (pSeated.get().getPlayerID().equals(SeatedAppPlayer.get().getPlayerID())))) {
						btnSit.setText("Leave");
						btnSit.setVisible(true);
						btnSit.setDisable(false);
						// Someone is sitting at position, I'm sitting, it's not me
					} else if ((pSeated.isPresent() && (SeatedAppPlayer.isPresent()
							&& (!pSeated.get().getPlayerID().equals(SeatedAppPlayer.get().getPlayerID()))))) {
						btnSit.setVisible(false);
						btnSit.setDisable(true);

					} else if ((pSeated.isEmpty() && (SeatedAppPlayer.isPresent()))) {
						btnSit.setVisible(false);
						btnSit.setDisable(true);
					} else if ((pSeated.isPresent() && (SeatedAppPlayer.isEmpty()))) {
						btnSit.setVisible(false);
						btnSit.setDisable(true);
					}
				}
			}
		}

	}

	@FXML
	private void btnClick(ActionEvent event) {
		AddCardToHbox("p1HBoxCards", BuildImage(10));
	}

	/**
	 * btnSit - execute this action after the Sit/Leave button is clicked
	 * @author BRG
	 * @version Lab #6
	 * @since Lab #6
	 * @param event
	 */
	@FXML
	private void btnSitLeave(ActionEvent event) {

		// extract the button from the event
		Button btnSit = (Button) event.getSource();
		// Find the Player
		this.mainApp.getAppPlayer()
				.setiPlayerPosition(Integer.parseInt(btnSit.getId().substring(btnSit.getId().length() - 1)));

		eAction eA = (btnSit.getText().contentEquals("Sit") ? eAction.Sit : eAction.Leave);

		// Create an Action
		Action act = new Action(eA, this.mainApp.getAppPlayer());

		// Send the Action to the Hub
		this.mainApp.messageSend(act);
	}

	/**
	 * BuildImage - Create an image view for a given iCardNbr
	 * 
	 * @author BRG
	 * @version Lab #6
	 * @since Lab #6
	 * @param iCardNbr
	 * @return
	 */
	private ImageView BuildImage(int iCardNbr) {

		String strImgPath = null;
		int iWidth = 72;
		int iHeight = 96;
		switch (iCardNbr) {
		case -1:
			strImgPath = "/img/b1fh.png";
			break;
		case -2:
			strImgPath = "/img/blank.png";
			break;
		case 0:
			strImgPath = "/img/b1fv.png";
			break;
		default:
			strImgPath = "/img/" + iCardNbr + ".png";
		}

		return new ImageView(new Image(getClass().getResourceAsStream(strImgPath), iWidth, iHeight, true, true));

	}

	/**
	 * getAllControls - get all controls of a particular control type, starting with a given root
	 * 
	 * @author BRG
	 * @version Lab #6
	 * @since Lab #6
	 * @param root - root control
	 * @param o - instance of given control type
	 * @return
	 */
	private ArrayList<Node> getAllControls(Parent root, Object o) {
		ArrayList<Node> nodeControl = new ArrayList<Node>();
		for (Node n : getAllNodes(parentNode)) {
			if (n.getClass() == o.getClass()) {
				nodeControl.add(n);
			}
		}

		return nodeControl;

	}

	/**
	 * getAllNodes - Find all the nodes (controls) in the form starting with the
	 * outer frame
	 * 
	 * @author BRG
	 * @version Lab #6
	 * @since Lab #6
	 * @param root - root control
	 * @return
	 */
	private ArrayList<Node> getAllNodes(Parent root) {
		ArrayList<Node> nodes = new ArrayList<Node>();
		addAllDescendents(root, nodes);
		return nodes;
	}

	/**
	 * addAllDescendents - Find all controls within a root
	 * 
	 * @author BRG
	 * @version Lab #6
	 * @since Lab #6
	 * @param root
	 * @return
	 */
	private void addAllDescendents(Parent parent, ArrayList<Node> nodes) {
		for (Node node : parent.getChildrenUnmodifiable()) {
			nodes.add(node);
			if (node instanceof Parent)
				addAllDescendents((Parent) node, nodes);
		}
	}

	/**
	 * SetLabelText - Find the control... set the text
	 * 
	 * @author BRG
	 * @version Lab #6
	 * @since Lab #6
	 * @param strLabelName
	 * @param strText
	 */
	private void SetLabelText(String strLabelName, String strText) {
		ArrayList<Node> nodes = getAllNodes(parentNode);

		for (Node n : nodes) {
			if (n instanceof Label) {
				Label l = (Label) n;
				if (l.getId().equals(strLabelName)) {
					l.setText(strText);
				}
			}
		}
	}

	private void AddCardToHbox(String HboxName, ImageView imgCard) {
		ArrayList<Node> nodes = getAllNodes(parentNode);
		for (Node n : nodes) {
			if (n instanceof HBox) {
				HBox hBox = (HBox) n;
				if ((hBox.getId() != null) && (hBox.getId().equals(HboxName))) {
					hBox.getChildren().add(imgCard);
				}
			}
		}
	}
}
