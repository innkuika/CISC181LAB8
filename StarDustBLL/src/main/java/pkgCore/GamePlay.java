package pkgCore;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.collect.MinMaxPriorityQueue;

import pkgEnum.eCardDestination;
import pkgEnum.eDrawCount;
import pkgEnum.eRank;
import pkgEnum.eSubstituteDeck;
import pkgEnum.eSuit;
import pkgException.DeckException;
import pkgException.HandException;

public class GamePlay {

	private Rule Rle;
	private ArrayList<Player> GamePlayers = new ArrayList<Player>();
	private HashMap<UUID, HandPoker> GameHand = new HashMap<UUID, HandPoker>();
	private ArrayList<Card> CommonCards = new ArrayList<Card>();
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
		GameDeck = new Deck();
	}

	public void Draw(Player p, CardDraw CD) throws DeckException, HandException {

		for (int crdCnt = 0; crdCnt < CD.getCardCount().getCardCount(); crdCnt++) {
			if (CD.getCardDestination() == eCardDestination.COMMON) {
				CommonCards.add(GameDeck.Draw());
			} else {
				GameHand.get(p.getPlayerID()).Draw(GameDeck);
			}
		}
	}

	/**
	 * @author BRG
	 * @version Lab #4
	 * @since Lab #4
	 * 
	 *        EvaluateGameHands - Find every hand in the GameHand map and evaluate
	 *        it.
	 * 
	 * @throws HandException
	 */
	public void EvaluateGameHands() throws HandException {
		ArrayList<HandPoker> pokerHands = new ArrayList<HandPoker>();
		Iterator<Map.Entry<UUID, HandPoker>> itr = GameHand.entrySet().iterator();
		while (itr.hasNext()) {
			Map.Entry<UUID, HandPoker> entry = itr.next();

			HandPoker hp = entry.getValue();
			pokerHands.addAll(hp.EvaluateHand(hp));
		}
	}

	/**
	 * getBestMadeHands - get the best made hands by Player.
	 * 
	 * @version Lab #5
	 * @since Lab #5
	 * 
	 * @param player
	 * @return
	 * @throws HandException
	 */
	public ArrayList<HandPoker> getBestMadeHands(Player player) throws HandException {
		return this.getBestHands(player, true);
	}

	/**
	 * getBestPossibleHands - get the best possible hands. These are hands that are
	 * made, but are made using a wild card.
	 * 
	 * @version Lab #5
	 * @since Lab #5
	 * @param player
	 * @return
	 * @throws HandException
	 */
	public ArrayList<HandPoker> getBestPossibleHands(Player player) throws HandException {
		
		MinMaxPriorityQueue<HandPoker> queue = MinMaxPriorityQueue
				  .orderedBy(HandPoker.hpComparator)
				  .maximumSize(10)
				  .create();
		
		for (HandPoker hp: this.getBestHands(player, false))
		{
			queue.add(hp);
		}

		return new ArrayList<HandPoker>(queue);
		
	}
	

	public HashSet<HandScorePoker> getUniqueHSP(Player p) throws HandException {
		HashSet<HandScorePoker> setHSP = new HashSet<HandScorePoker>();
		
		for (HandPoker hp: this.getBestPossibleHands(p))
		{
			setHSP.add(hp.getHandScorePoker());
		}
		return setHSP;
	}
	/**
	 * getBestHands - will pass back an array list of the best hands. If bMadeHand
	 * is true, it's looking for a hand that doesn't have jokers... which means the
	 * hand is an actual hand
	 * 
	 * @param player    - given player
	 * @param bMadeHand - if true, ensure the found hand has no jokers.
	 * @return
	 * @throws HandException
	 */
	private ArrayList<HandPoker> getBestHands(Player player, boolean bMadeHand) throws HandException {

		ArrayList<HandPoker> pokerHands = new ArrayList<HandPoker>();
		Iterator<Map.Entry<UUID, HandPoker>> itr = GameHand.entrySet().iterator();
		while (itr.hasNext()) {
			Map.Entry<UUID, HandPoker> entry = itr.next();

			if (player.getPlayerID().equals(entry.getKey())) {
				HandPoker hp = entry.getValue();
				try {
					pokerHands.addAll(hp.EvaluateHand(hp));
				} catch (HandException e) {
					e.printStackTrace();
					throw e;
				}
			}
		}
		
		//isNatural = false when one of the cards used to make the hand was a substitution
		
		pokerHands = (ArrayList<HandPoker>) pokerHands.stream()
				.filter(x -> x.getHandScorePoker().isNatural() == bMadeHand)
				.collect(Collectors.toList());
		
		Collections.sort(pokerHands);
		
		return pokerHands;
	}

	public ArrayList<Card> getCommonCards() {
		int iSize = CommonCards.size();
		ArrayList<Card> commonCards = (ArrayList<Card>) CommonCards.clone();
		for (int i = iSize; i < this.getRle().getCommunityCardsMax(); i++) {
			commonCards.add(new Card(eSuit.JOKER, eRank.JOKER, eSubstituteDeck.SUBSTITUTE));
		}
		return commonCards;
	}

	/**
	 * @author BRG
	 * @version Lab #4
	 * @since Lab #4 GetGamePlayer - return the Player object for a given PlayerID
	 * @param PlayerID - ID for the Player
	 * @return - Player object
	 */
	private Player GetGamePlayer(UUID PlayerID) {
		for (Player p : GamePlayers) {
			if (p.getPlayerID() == PlayerID)
				return p;
		}
		return null;
	}

	/**
	 * @author BRG
	 * @version Lab #4
	 * @since Lab #4
	 * 
	 *        GetPlayersHand - return the Hand in the GameHand hashmap for a given
	 *        player
	 * @param player
	 * @return
	 */
	public HandPoker GetPlayersHand(Player player) {
		return GameHand.get(player.getPlayerID());
	}

	/**
	 * @author BRG
	 * @version Lab #4
	 * @since Lab #4
	 * 
	 *        getRle - Get the rule for the game. It's set in the constructor
	 * @return
	 */
	public Rule getRle() {
		return Rle;
	}

	/**
	 * @author BRG
	 * @version Lab #4
	 * @since Lab #4
	 * 
	 *        isMadeHandBestPossibleHand - return 'true' if the BestMadeHand is one
	 *        of the BestPossibleHands
	 * @param player
	 * @return
	 * @throws HandException
	 */
	public boolean isMadeHandBestPossibleHand(Player player) throws HandException {

		if (getBestMadeHands(player).get(0).getHS().equals(this.getBestPossibleHands(player).get(0).getHS())) {
			return true;
		}
		return false;
	}

	/**
	 * @author BRG
	 * @version Lab #4
	 * @since Lab #4
	 * 
	 *        StartGame - Create a new HandPoker for each player, put it in the
	 *        GameHand map, execute the first Draw
	 * 
	 * @throws DeckException
	 * @throws HandException
	 */
	public void StartGame() throws DeckException, HandException {
		for (Player p : GamePlayers) {
			HandPoker hp = new HandPoker(p, this);
			GameHand.put(p.getPlayerID(), hp);
			Draw(p, this.Rle.getCardDraw(eDrawCount.FIRST));
		}
	}

	/**
	 * @author BRG
	 * @version Lab #4
	 * @since Lab #4
	 * 
	 *        GetGameWinners - Return an ArrayList of players with the winning hand.
	 *        Could be a tie...
	 * @return
	 */
	public ArrayList<Player> GetGameWinners() {
		ArrayList<Player> WinningPlayers = new ArrayList<Player>();
		/*
		 * 
		 * ArrayList<HandPoker> GameHands = new ArrayList<HandPoker>(); for (HandPoker
		 * PlayerHand : BestMadeHand.values()) { GameHands.add(PlayerHand); }
		 * Collections.sort(GameHands); HandPoker WinningHand = GameHands.get(0);
		 * 
		 * Iterator<Map.Entry<UUID, HandPoker>> itr =
		 * BestMadeHand.entrySet().iterator(); while (itr.hasNext()) { Map.Entry<UUID,
		 * HandPoker> entry = itr.next(); if (entry.getValue().equals(WinningHand)) {
		 * WinningPlayers.add(GetGamePlayer(entry.getKey())); } }
		 */
		return WinningPlayers;
	}

	/**
	 * @author BRG
	 * @version Lab #4
	 * @since Lab #4
	 * 
	 *        PutGameHand - puts a hand to the GameHand map
	 * @return
	 */

	private void PutGameHand(UUID PlayerID, HandPoker hp) {
		GameHand.put(PlayerID, hp);
	}

	/**
	 * @author BRG
	 * @version Lab #4
	 * @since Lab #4
	 * 
	 *        setCommonCards - set the common cards.
	 * @param cards
	 */
	private void setCommonCards(ArrayList<Card> cards) {
		this.CommonCards.clear();
		this.CommonCards.addAll(cards);
	}

}
