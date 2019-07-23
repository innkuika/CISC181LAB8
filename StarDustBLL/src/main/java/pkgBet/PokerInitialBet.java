package pkgBet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;

import pkgCore.Card;
import pkgEnum.eBetAction;
import pkgEnum.eBetRound;
import pkgEnum.eRank;
import pkgEnum.eSuit;

public class PokerInitialBet extends Bet implements Comparable {

	/*
	 * https://46ej48mtoih19noij34d8gp3-wpengine.netdna-ssl.com/wp-content/uploads/
	 * 2015/08/range4.png
	 */
	private Card Card1;
	private Card Card2;
	private int iBetPositon;
	private int iTotalPlayers;
	private ArrayList<DeckTwoCard> TwoCardDeck = new ArrayList<DeckTwoCard>();

	public PokerInitialBet(int iBetPosition, 
			int iPositionCnt, 
			eBetAction ebt, 
			eBetRound ebr, 
			Card Card1, 
			Card Card2) {
		super(iBetPosition, iPositionCnt, ebt, ebr);

		this.iBetPositon = iBetPosition;
		this.iTotalPlayers = iTotalPlayers;
		this.Card1 = Card1;
		this.Card2 = Card2;
		
	}

	private boolean bSameRank() {
		if (Card1.geteRank().equals(Card2.geteRank())) {
			return true;
		}
		return false;
	}

	@Override
	public int compareTo(Object o) {

		PokerInitialBet PIB = (PokerInitialBet) o;

		if (PIB.bSameRank() != this.bSameRank()) {
			return 1;
		}
		if (PIB.Card1.geteRank().getiRankNbr() - this.Card1.geteRank().getiRankNbr() != 0) {
			return PIB.Card1.geteRank().getiRankNbr() - this.Card1.geteRank().getiRankNbr();
		}
		if (PIB.Card2.geteRank().getiRankNbr() - this.Card2.geteRank().getiRankNbr() != 0) {
			return PIB.Card2.geteRank().getiRankNbr() - this.Card2.geteRank().getiRankNbr();
		}

		return 0;
	}

	/**
	 * SetTwoCardDeck - the purpose of this method is to set class attribute TwoCardDeck
	 * 
	 * TwoCardDeck should have 169 cards:
	 * -- one entry for each pair (22, 33, 44... AA)
	 * -- one entry for each two card non-pair with same suit.  I only need one suited non-pair...
	 * --			Example... 10Q/9Q... but don't need 10C/9C
	 * -- one entry for each two card non-pair off-suit.  I only one need one off suit non-pair...
	 * --			Example... 10Q/9C... but don't need 10Q/9S.
	 */
	
	public void SetTwoCardDeck() {

		HashSet<DeckTwoCard> hsTwoCard = new HashSet<DeckTwoCard>();

		for (eSuit eSuit1 : eSuit.values()) {
			for (eRank eRank1 : eRank.values()) {
				for (eSuit eSuit2 : eSuit.values()) {
					for (eRank eRank2 : eRank.values()) {
						Card c1 = new Card(eSuit1, eRank1);
						Card c2 = new Card(eSuit2, eRank2);
						if (!c1.equals(c2)) {
							DeckTwoCard dtc = new DeckTwoCard(c1, c2);
							hsTwoCard.add(dtc);
						}
					}
				}
			}
		}
		
		TwoCardDeck = new ArrayList<DeckTwoCard>(hsTwoCard);
		

		/*
		 * ArrayList<DeckTwoCard> dtcList = new ArrayList<DeckTwoCard>(hsTwoCard)
		 * .stream() .filter(x -> x.isbSameSuit() == true)
		 * .collect(Collectors.toCollection(ArrayList::new));
		 */
		ArrayList<DeckTwoCard> dtcList = new ArrayList<DeckTwoCard>(hsTwoCard);
		
		dtcList = dtcList.stream() .filter(x -> x.isbSameRank() == true)
				 .collect(Collectors.toCollection(ArrayList::new));
		
		Collections.sort(dtcList);
		
		for (DeckTwoCard d : dtcList) {
			System.out.print(d.getEr1() + " ");
			System.out.print(d.getEr2() + " ");
			System.out.print(d.isbSameRank() + " ");
			System.out.println(d.isbSameSuit() + " ");
		}
		System.out.println(dtcList.size());
	}

}
