package pkgCore;

import java.util.ArrayList;

import pkgCoreInterface.iCardDraw;

public class DrawResult {

	private CardDraw CD;
	private Player p;
	private ArrayList<iCardDraw> cards;
	
	public DrawResult(CardDraw cD, Player p, ArrayList<iCardDraw> cards) {
		super();
		CD = cD;
		this.p = p;
		this.cards = cards;
	}

	public CardDraw getCD() {
		return CD;
	}

	public Player getP() {
		return p;
	}

	public ArrayList<iCardDraw> getCards() {
		return cards;
	}
	
	
	
	
}
