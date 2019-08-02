package pkgCore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import pkgEnum.eCardDestination;
import pkgEnum.eDrawCount;
import pkgEnum.eRank;
import pkgEnum.eSuit;
import pkgException.DeckException;

public class GamePlay {

	private Rule Rle;
	private ArrayList<Player> GamePlayers = new ArrayList<Player>();
	private HashMap<UUID, HandPoker> GameHand = new HashMap<UUID, HandPoker>();
	private ArrayList<Card> CommonCards = new ArrayList<Card>();
	
	private HashMap<UUID, HandPoker> BestMadeHand = new HashMap<UUID, HandPoker>();
	private HashMap<UUID, HandPoker> BestPossibleHand = new HashMap<UUID, HandPoker>();
	
	
	protected void SetBestMadeHand(UUID PlayerID, HandPoker HandPoker)
	{
		BestMadeHand.put(PlayerID,  HandPoker);
	}
	
	protected void SetBestPossibleHand(UUID PlayerID, HandPoker HandPoker)
	{
		BestPossibleHand.put(PlayerID,  HandPoker);
	}
	
	
	public HandPoker getBestMadeHand(Player p) {
		return BestMadeHand.get(p.getPlayerID());
	}

	public HandPoker getBestPossibleHand(Player p) {
		return BestPossibleHand.get(p.getPlayerID());
	}




	private Deck GameDeck;

	/**
	 * GamePlay - Create an instance of GamePlay. For every player in the table, add
	 * them to the game Set the GameDeck.
	 * 
	 * @param t
	 * @param rle
	 */
	public GamePlay(Table t, Rule rle) {
		this.Rle = rle;
		GamePlayers.addAll(t.getTablePlayers());
		GameDeck = t.setTableDeck(new Deck());
	}
	
	

	public ArrayList<Card> getCommonCards() {
		int iSize = CommonCards.size();
		ArrayList<Card> commonCards = (ArrayList<Card>) CommonCards.clone();
		for (int i = iSize; i < 5; i++)
		{
			commonCards.add(new Card(eSuit.JOKER, eRank.JOKER));
		}
		return commonCards;
	}



	public Rule getRle() {
		return Rle;
	}



	public void StartGame() throws DeckException {
		for (Player p : GamePlayers) {
			HandPoker hp = new HandPoker(p, this);
			GameHand.put(p.getPlayerID(), hp);
			Draw(p, this.Rle.getCardDraw(eDrawCount.FIRST));
		}
	}

	public void Draw(Player p, CardDraw CD) throws DeckException {

		for (int crdCnt = 0; crdCnt < CD.getCardCount().getCardCount(); crdCnt++) {
			if (CD.getCardDestination() == eCardDestination.COMMON) {
				CommonCards.add(GameDeck.Draw());
			} else {
				GameHand.get(p.getPlayerID()).Draw(GameDeck);
			}
		}
	}
	
	public HandPoker GetPlayersHand(Player p)
	{
		return GameHand.get(p.getPlayerID());
	}


	
	
}
