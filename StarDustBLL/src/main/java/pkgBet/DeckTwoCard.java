package pkgBet;

import java.util.Objects;

import pkgCore.Card;
import pkgCore.HandPoker;
import pkgCore.HandScorePoker;
import pkgEnum.eRank;

public class DeckTwoCard implements Comparable {

	private eRank er1;
	private eRank er2;
	private boolean bSameSuit;
	private boolean bSameRank;

	public DeckTwoCard(Card card1, Card card2) {
		if (card1.geteRank().getiRankNbr() <= card2.geteRank().getiRankNbr()) {
			this.er1 = card1.geteRank();
			this.er2 = card2.geteRank();
		} else {
			this.er1 = card2.geteRank();
			this.er2 = card1.geteRank();
		}

		if (card1.geteSuit().equals(card2.geteSuit())) {
			this.bSameSuit = true;
		} else {
			this.bSameSuit = false;
		}

		this.bSameRank = (card1.geteRank().equals(card2.geteRank()));
	}

	public eRank getEr1() {
		return er1;
	}

	public void setEr1(eRank er1) {
		this.er1 = er1;
	}

	public eRank getEr2() {
		return er2;
	}

	public void setEr2(eRank er2) {
		this.er2 = er2;
	}

	public boolean isbSameSuit() {
		return bSameSuit;
	}

	public boolean isbSameRank() {
		return bSameRank;
	}

	@Override
	public int hashCode() {
		return Objects.hash(er1, er2, bSameSuit);
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

		if ((c.er1.equals(this.er1)) && (c.er2.equals(this.er2))
				&& (c.bSameSuit == this.bSameSuit))

			return true;

		return false;
	}

	@Override
	public int compareTo(Object o) {

		DeckTwoCard dtc = (DeckTwoCard) o;

		if (dtc.er1.getiRankNbr() - this.er1.getiRankNbr() != 0)
			return dtc.er1.getiRankNbr() - this.er1.getiRankNbr();

		if (dtc.er2.getiRankNbr() - this.er2.getiRankNbr() != 0)
			return dtc.er2.getiRankNbr() - this.er2.getiRankNbr();

		return 0;
	}
}
