package pkgBetStrategy;

import java.util.LinkedList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

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

	/**
	 * getBettingStrategy - return a LinkedList of BettingStrategy 
	 * for given BetRoundNbr, BetPosition and eBetRound 
	 * 
	 * If no strategy(s) are found, return null
	 * 
	 * @version Lab #3
	 * @since Lab #3
	 * @param BetRoundNbr
	 * @param BetPositionNbr
	 * @param eBR
	 * @return
	 */
	public static LinkedList<BettingStrategy> getBettingStrategy( 
			int BetPositionNbr,
			eBetRound eBR)
	{
		LinkedList<BettingStrategy> lstBS = null;
		
		PlayerPosition pp = BetRound.getBetRound(eBR).getPlayerPosition().stream()
				.filter(x -> x.getBetPositionNbr() == BetPositionNbr).findAny().orElse(null);

		lstBS = pp.getBettingStrategy();
		
		return lstBS;
	}
	
	/**
	 * getBettingStrategy - return a single BettingStrategy for a 
	 * given BetRoundNbr, BetPositionNbr, eBetRound and two Cards
	 * 
	 * If no BetStrategy is found, return null
	 * 
	 * @param BetRoundNbr
	 * @param BetPositionNbr
	 * @param eBR
	 * @param c1
	 * @param c2
	 * @return
	 */
	public static BettingStrategy getBettingStrategy( 
			int BetPositionNbr, 
			eBetRound eBR, 
			Card c1,
			Card c2,
			int iCurrentBetAmt,
			int iCurrentPotAmt,
			int iCurrentStakeAmt) {
		
		BettingStrategy bsFound = null;
		eBetAction eCurrentBetAction = (iCurrentBetAmt > 0) ? eBetAction.BET : eBetAction.CHECK;

		//	This code will ensure c1 is the higher rank.		
		if (c1.geteRank().getiRankNbr() < c2.geteRank().getiRankNbr())
		{	
			Card c0 = c1;
			c1=c2;
			c2=c0;
			c0 = null;
		}

		boolean bSameSuit = (c1.geteSuit().equals(c2.geteSuit()) ? true : false);
		boolean bSameRank = (c1.geteRank().equals(c2.geteRank()) ? true : false);
		
		LinkedList<BettingStrategy> lstBS = getBettingStrategy(BetPositionNbr, eBR);
		
		for (BettingStrategy bs : lstBS) {
			if ((c1.geteRank().getiRankNbr() >= bs.getCard1Rank().getiRankNbr())
					&& (c2.geteRank().getiRankNbr() >= bs.getCard2Rank().getiRankNbr())
					&& (bSameSuit == bs.isSameSuit)
					&& (bSameRank == bs.isSameRank)) {
				
				bsFound = bs;
				bsFound.seteBetPlayerAction(bs.geteBetCurrentAction());
				if (eCurrentBetAction == bs.geteBetCurrentAction())
				{					
					break;
				}				
			}
		}
		
		if (bsFound != null)
		{
			//	If the player's action is a check, don't set the values for
			//	bet amon, return
			if (bsFound.geteBetPlayerAction() == eBetAction.CHECK)
				return bsFound;
			
			BetAmount ba = bsFound.getBetAmount().DetermineBet(bsFound, iCurrentBetAmt, iCurrentPotAmt, iCurrentStakeAmt);
			
			if (ba == null)
			{
				bsFound.seteBetPlayerAction(eBetAction.FOLD);					
			}
			else
			{
				bsFound.setBetAmount(ba);
			}							
		}

		return bsFound;
	}

}
