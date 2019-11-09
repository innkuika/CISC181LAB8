package app.hub;

import java.io.IOException;

import netgame.common.Hub;
import pkgCore.Action;
import pkgCore.Table;

public class GameHub extends Hub {

	private Table HubPokerTable = null;
	// private GamePlay HubGamePlay = null;

	public GameHub(int port) throws IOException {
		super(port);
		this.setAutoreset(true);
	}

	@Override
	public void messageReceived(int ClientID, Object message) {

		if (HubPokerTable == null)
			HubPokerTable = new Table("Poker");
		

		if (message instanceof Action)
		{
			Action act = (Action)message;
			
			switch (act.geteAction())
			{
			case TableState:
				resetOutput();
				sendToAll(HubPokerTable);
				break;
			case Sit:
				HubPokerTable.AddPlayerToTable(act.getActPlayer());
					resetOutput();
 				sendToAll(HubPokerTable);
				break;
			case Leave:
				HubPokerTable.RemovePlayerFromTable(act.getActPlayer());
				resetOutput();
				sendToAll(HubPokerTable);
				break;
			}
		}
		
		
		
		
		
		
		
//		if (HubPokerTable == null)
//			HubPokerTable = new Table();
//		
//		if (message instanceof Action) {
//			
//			Action a = (Action)message;
//
//			switch (a.geteAction()) {
//			case Sit:
//				HubPokerTable.AddPlayerToTable(a.getActPlayer());
//				resetOutput();
//				sendToAll(HubPokerTable);				
//				break;
//			case Leave:
//				HubPokerTable.RemovePlayerFromTable(a.getActPlayer());
//				resetOutput();
//				sendToAll(HubPokerTable);
//				break;
//			case TableState:
//				resetOutput();
//				sendToAll(HubPokerTable);
//			case GameState:
//				//TODO: Implement this
//				break;
//			case StartGameBlackJack:
//				//TODO: Implement this	
//				eGameType = eGameType.BLACKJACK;
//
//				break;
//			case Draw:
//				//TODO: Implement this
//				break;
//			}

		// }
	}

}