package pkgBetStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import pkgCore.Card;
import pkgEnum.eBetAction;
import pkgEnum.eBetRound;
import pkgEnum.eHandStrength;
import pkgEnum.eRank;

@XmlAccessorType(XmlAccessType.FIELD)
public class BettingStrategy {

	@XmlElement
	private int StrategyNbr;
	@XmlElement
	private eHandStrength eHandStrength;
	@XmlElement 
	private ArrayList<eRank> lstCardRank = new ArrayList<eRank>();
	@XmlElement
	private boolean isSameSuit;
	@XmlElement
	private boolean isSameRank;
	@XmlElement
	private eBetAction eBetCurrentAction;
	@XmlTransient
	private eBetAction eBetPlayerAction;
	@XmlElement
	private BetAmount BetAmount;

	public int getStrategyNbr() {
		return StrategyNbr;
	}

	public void setStrategyNbr(int strategyNbr) {
		StrategyNbr = strategyNbr;
	}

	public ArrayList<eRank> getCardRanks() {
		return lstCardRank;
	}

	public void setCardRanks(ArrayList<eRank> cardRanks) {
		lstCardRank = cardRanks;
	}

	public void addCardRank(eRank cardRank)
	{
		this.lstCardRank.add(cardRank);
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

	public BetAmount getBetAmount() {
		return BetAmount;
	}

	public void setBetAmount(BetAmount betAmount) {
		BetAmount = betAmount;
	}

	public eBetAction geteBetCurrentAction() {
		return eBetCurrentAction;
	}

	public void seteBetCurrentAction(eBetAction eBetCurrentAction) {
		this.eBetCurrentAction = eBetCurrentAction;
	}

	public eBetAction geteBetPlayerAction() {
		return eBetPlayerAction;
	}

	public void seteBetPlayerAction(eBetAction eBetPlayerAction) {
		this.eBetPlayerAction = eBetPlayerAction;
	}

	public eHandStrength geteHandStrength() {
		return eHandStrength;
	}

	public void seteHandStrength(eHandStrength eHandStrength) {
		this.eHandStrength = eHandStrength;
	}


	/**
	 * getBettingStrategy - return a LinkedList of BettingStrategy for given
	 * BetRoundNbr, BetPosition and eBetRound
	 * 
	 * If no strategy(s) are found, return null
	 * 
	 * @version Lab #3
	 * @since Lab #3
	 * @param BetRoundNbr
	 * @param BetPositionNbr
	 * @param eBR
	 * @return
	 * @throws Exception 
	 */
	public static LinkedList<BettingStrategy> getBettingStrategy(int BetPositionNbr, eBetRound eBR) throws Exception {
		LinkedList<BettingStrategy> lstBS = null;

		PlayerPosition pp = BetRound.getBetRound(eBR).getPlayerPosition().stream()
				.filter(x -> x.getBetPositionNbr() == BetPositionNbr).findAny().orElse(null);

		lstBS = pp.getBettingStrategy();

		return lstBS;
	}



}
