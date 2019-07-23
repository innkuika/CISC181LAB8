package pkgBetStrategy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import pkgEnum.eBetAction;
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
	private eBetAction BetAction;
	
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

	public eBetAction getBetAction() {
		return BetAction;
	}

	public void setBetAction(eBetAction betAction) {
		BetAction = betAction;
	}

	public BetAmount getBetAmount() {
		return BetAmount;
	}

	public void setBetAmount(BetAmount betAmount) {
		BetAmount = betAmount;
	}	
	
	
}
