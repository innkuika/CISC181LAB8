package pkgCore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import pkgException.DeckException;

public class GamePlay {

	private ArrayList<Player> GamePlayers = new ArrayList<Player>();
	private HashMap<UUID, Hand> GameHand = new HashMap<UUID, Hand>();
	private Deck GameDeck;
	
	public GamePlay(ArrayList<Player> players)
	{
		GamePlayers.addAll(players);
		GameDeck = new Deck();
	}
	
	public void Draw (Player p) throws DeckException
	{
		HandPoker HP = (HandPoker)GameHand.get(p.getPlayerID());
		HP.Draw(GameDeck);
	}
}
