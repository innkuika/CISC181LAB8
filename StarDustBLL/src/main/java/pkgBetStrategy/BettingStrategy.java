package pkgBetStrategy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import pkgCore.Card;
import pkgEnum.eBetAction;
import pkgEnum.eBetRound;
import pkgEnum.eRank;

@XmlAccessorType(XmlAccessType.FIELD)
public class BettingStrategy {

	@XmlElement
	private int StrategyNbr;
	@XmlElement
	private eRank Card1Rank;
	@XmlElement
	private eRank Card2Rank;
	@XmlElement
	private boolean isSameSuit;
	@XmlElement
	private boolean isSameRank;

	@XmlElement
	private eBetAction eBetAction;

	@XmlElement
	private BetAmount BetAmount;

	public int getStrategyNbr() {
		return StrategyNbr;
	}

	public void setStrategyNbr(int strategyNbr) {
		StrategyNbr = strategyNbr;
	}

	public eRank getCard1Rank() {
		return Card1Rank;
	}

	public void setCard1Rank(eRank card1Rank) {
		Card1Rank = card1Rank;
	}

	public eRank getCard2Rank() {
		return Card2Rank;
	}

	public void setCard2Rank(eRank card2Rank) {
		Card2Rank = card2Rank;
	}

	public boolean isSameSuit() {
		return isSameSuit;
	}

	public void setSameSuit(boolean isSameSuit) {
		this.isSameSuit = isSameSuit;
	}

	public boolean isSameRank() {
		return isSameRank;
	}

	public void setSameRank(boolean isSameRank) {
		this.isSameRank = isSameRank;
	}



	public eBetAction geteBetAction() {
		return eBetAction;
	}

	public void seteBetAction(eBetAction eBetAction) {
		this.eBetAction = eBetAction;
	}

	public BetAmount getBetAmount() {
		return BetAmount;
	}

	public void setBetAmount(BetAmount betAmount) {
		BetAmount = betAmount;
	}

	public static BettingStrategy getBettingStrategy(int BetRoundNbr, int BetPositionNbr, eBetRound eBR, Card c1,
			Card c2) {

		BettingStrategy bsFound = null;

		PlayerPosition pp = BetRound.getBetRound(BetRoundNbr, eBR).getPlayerPosition().stream()
				.filter(x -> x.getBetPositionNbr() == BetPositionNbr).findAny().orElse(null);

		for (BettingStrategy bs : pp.getBettingStrategy()) {
			if ((c1.geteRank().getiRankNbr() >= bs.getCard1Rank().getiRankNbr())
					&& (c2.geteRank().getiRankNbr() >= bs.getCard2Rank().getiRankNbr())) {
				// ba = bs.getBetAmount();
				bsFound = bs;			
			}
		}

		return bsFound;
	}

}
