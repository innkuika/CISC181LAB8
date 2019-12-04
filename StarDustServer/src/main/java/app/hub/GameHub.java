package app.hub;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import netgame.common.Hub;
import pkgCore.Action;
import pkgCore.DrawResult;
import pkgCore.GamePlay;
import pkgCore.HandPoker;
import pkgCore.HandScorePokerSummary;
import pkgCore.Player;
import pkgCore.Rule;
import pkgCore.Table;
import pkgEnum.eAction;
import pkgEnum.eGame;
import pkgEnum.eSuit;
import pkgException.DeckException;
import pkgException.HandException;

public class GameHub extends Hub {

	private Table HubPokerTable = null;
	private GamePlay HubGamePlay = null;

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
			case Bet:
				break;
			case Deal:
				break;
			case Fold:
				break;

			case Raise:
				break;
			case ScoreGame:
				break;
			case StartGamePoker:
				try {
					HubGamePlay = new GamePlay(HubPokerTable, new Rule(eGame.TexasHoldEm));
					HubGamePlay.StartGame();
				} catch (DeckException e) {
					e.printStackTrace();
				} catch (HandException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}				
			case Draw:
				try {
					HubGamePlay.Draw();
				} catch (DeckException e) {
					e.printStackTrace();
				} catch (HandException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}				
				for (Player p: this.HubGamePlay.getGamePlayers())
				{					
					ArrayList<DrawResult> DR = HubGamePlay.getDrawResult(p);
					resetOutput();
					sendToOne(p.getClientID(),DR);
				}				
			case GameState:
				
				if (HubGamePlay.getCommonCards().stream().filter(x -> x.geteSuit().equals(eSuit.JOKER)).collect(Collectors.toList()).size() <=2)
				{
					for (Player p: this.HubGamePlay.getGamePlayers())
					{					
						try {
							ArrayList<HandPoker> BestMadeHands = HubGamePlay.getBestMadeHands(p);
							ArrayList<HandPoker> BestPossibleHands = HubGamePlay.getBestPossibleHands(p);
							HandScorePokerSummary HSPS = new HandScorePokerSummary(BestMadeHands, BestPossibleHands,p);	
							resetOutput();
							sendToOne(p.getClientID(),HSPS);
							
						} catch (HandException e) {
							e.printStackTrace();
						}
					}		
				}
				
				
				break;
			default:
				break;
			}
		}
		
		

	}

}