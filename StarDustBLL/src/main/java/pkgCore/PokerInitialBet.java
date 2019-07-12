package pkgCore;

import java.util.ArrayList;

import pkgEnum.eRank;

public class PokerInitialBet implements Comparable {

	/*
	 * https://46ej48mtoih19noij34d8gp3-wpengine.netdna-ssl.com/wp-content/uploads/
	 * 2015/08/range4.png
	 */
	private eRank CardRank1;
	private eRank CardRank2;
	private int iBetPositon;
	private int iTotalPlayers;

	public PokerInitialBet(int iBetPosition, int iTotalPlayers, eRank CardRank1, eRank CardRank2) {
		this.iBetPositon = iBetPosition;
		this.iTotalPlayers = iTotalPlayers;
		this.CardRank1 = CardRank1;
		this.CardRank2 = CardRank2;
	}

	public eRank getCardRank1() {
		return CardRank1;
	}

	public void setCardRank1(eRank cardRank1) {
		CardRank1 = cardRank1;
	}

	public eRank getCardRank2() {
		return CardRank2;
	}

	public void setCardRank2(eRank cardRank2) {
		CardRank2 = cardRank2;
	}

	private boolean bSameRank() {
		if (CardRank1.equals(CardRank2)) {
			return true;
		}
		return false;
	}

	public ArrayList<PokerInitialBet> getBettingArray() {
		ArrayList<PokerInitialBet> betArray = new ArrayList<PokerInitialBet>();
		for (eRank eR1 : eRank.values()) {
			for (eRank eR2 : eRank.values()) {
				PokerInitialBet PIB = new PokerInitialBet(this.iBetPositon, this.iTotalPlayers, eR1, eR2);
				betArray.add(PIB);
			}
		}
		return betArray;
	}

	@Override
	public int compareTo(Object o) {

		PokerInitialBet PIB = (PokerInitialBet)o;
		
		if (PIB.bSameRank() != this.bSameRank())
		{
			return 1;
		}	
		if (PIB.CardRank1.getiRankNbr() - this.CardRank1.getiRankNbr() != 0)
		{
			return PIB.CardRank1.getiRankNbr() - this.CardRank1.getiRankNbr();
		}
		if (PIB.CardRank2.getiRankNbr() - this.CardRank2.getiRankNbr() != 0)
		{
			return PIB.CardRank2.getiRankNbr() - this.CardRank2.getiRankNbr();
		}		
		
	
		
		return 0;
		
	}
}
