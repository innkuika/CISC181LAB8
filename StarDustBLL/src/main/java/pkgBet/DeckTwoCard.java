package pkgBet;

import java.util.Objects;

import pkgCore.Card;
import pkgCore.HandPoker;
import pkgCore.HandScorePoker;

public class DeckTwoCard implements Comparable {

	private Card c1;
	private Card c2;
	private boolean bSameSuit;
	private boolean bSameRank;

	public DeckTwoCard(Card card1, Card card2) {
		if (card1.geteRank().getiRankNbr() <= card2.geteRank().getiRankNbr()) {
			this.c1 = card1;
			this.c2 = card2;
		} else {
			this.c1 = card2;
			this.c2 = card1;
		}

		if (this.c1.geteSuit().equals(this.c2.geteSuit())) {
			this.bSameSuit = true;
		} else {
			this.bSameSuit = false;
		}
		
		this.bSameRank = (this.c1.geteRank().equals(this.c2.geteRank()));
	}

	
	public Card getC1() {
		return c1;
	}


	public Card getC2() {
		return c2;
	}


	public boolean isbSameSuit() {
		return bSameSuit;
	}
	


	public boolean isbSameRank() {
		return bSameRank;
	}


	@Override
	public int hashCode() {
		return Objects.hash(c1.geteRank(), c2.geteRank(), bSameSuit);
	}

	@Override
	public boolean equals(Object o) {
		// If the object is compared with itself then return true
		if (o == this) {
			return true;
		}

		/*
		 * Check if o is an instance of Complex or not "null instanceof [type]" also
		 * returns false
		 */
		if (!(o instanceof DeckTwoCard)) {
			return false;
		}

		// typecast o to Complex so that we can compare data members
		DeckTwoCard c = (DeckTwoCard) o;

		if ((c.c1.geteRank().equals(this.c1.geteRank())) && (c.c2.geteRank().equals(this.c2.geteRank()))
				&& (c.bSameSuit == this.bSameSuit))

			return true;

		return false;
	}

	@Override
	public int compareTo(Object o) {

		DeckTwoCard dtc = (DeckTwoCard) o;

		if (dtc.c1.geteRank().getiRankNbr() - this.c1.geteRank().getiRankNbr() != 0)
			return dtc.c1.geteRank().getiRankNbr() - this.c1.geteRank().getiRankNbr();

		if (dtc.c2.geteRank().getiRankNbr() - this.c2.geteRank().getiRankNbr() != 0)
			return dtc.c2.geteRank().getiRankNbr() - this.c2.geteRank().getiRankNbr();

		return 0;
	}
}
