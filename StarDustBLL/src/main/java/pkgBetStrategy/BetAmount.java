package pkgBetStrategy;

import pkgEnum.eBetAction;
import pkgEnum.eBetRound;
import pkgEnum.eBetType;

import java.security.SecureRandom;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import pkgCore.Card;
import pkgEnum.eBetType;

@XmlAccessorType(XmlAccessType.FIELD)
public class BetAmount {

	@XmlElement
	private double iBetMinPct;
	@XmlElement
	private double iBetMaxPct;
	@XmlElement
	private eBetType eBetType;
	
	@XmlTransient
	private int BetAmt;
	

	public double getiBetMinPct() {
		return iBetMinPct;
	}

	public void setiBetMinPct(double iBetMinPct) {
		this.iBetMinPct = iBetMinPct;
	}

	public double getiBetMaxPct() {
		return iBetMaxPct;
	}

	public void setiBetMaxPct(double iBetMaxPct) {
		this.iBetMaxPct = iBetMaxPct;
	}

	public eBetType geteBetType() {
		return eBetType;
	}

	public void seteBetType(eBetType eBetType) {
		this.eBetType = eBetType;
	}

	/*
	 * public static int GetBetAmt(int BetRoundNbr, int BetPositionNbr, eBetRound
	 * eBR, Card c1, Card c2, int CurrentBetAmount, int PotAmount, int StakeAmount)
	 * {
	 * 
	 * BettingStrategy bs = BettingStrategy.getBettingStrategy(BetRoundNbr,
	 * BetPositionNbr, eBR, c1, c2);
	 * 
	 * BetAmount ba = bs.getBetAmount(); return ba.GetBetAmt(CurrentBetAmount,
	 * PotAmount, StakeAmount); }
	 */

	public int getBetAmt() {
		return BetAmt;
	}

	public void setBetAmt(int betAmt) {
		BetAmt = betAmt;
	}

	public BetAmount DetermineBet(BettingStrategy bs,
			int CurrentBetAmount, 
			int PotAmount, 
			int StakeAmount)
	{
		int BetAmt = 0;
		double BetMinPct = this.getiBetMinPct();
		double BetMaxPct = this.getiBetMaxPct();
		int iBetMinAmount = 0;
		int iBetMaxAmount = 0;
		
		SecureRandom random = new SecureRandom();

		if (this.geteBetType() == pkgEnum.eBetType.POT) {
			iBetMinAmount = (int) (PotAmount * (BetMinPct * 0.01));			
			iBetMaxAmount = (int) (PotAmount * (BetMaxPct * 0.01));
		} else if (this.geteBetType() == pkgEnum.eBetType.STAKE) {
			iBetMinAmount = (int) (StakeAmount * (BetMinPct * 0.01));
			iBetMaxAmount = (int) (StakeAmount * (BetMaxPct * 0.01));
		}
		
		if (iBetMinAmount < CurrentBetAmount)
			iBetMinAmount = CurrentBetAmount;
		
		if (iBetMinAmount > iBetMaxAmount)
			return null;
		
		this.BetAmt = (random.nextInt(iBetMaxAmount - iBetMinAmount + 1) + iBetMinAmount);
		
		return this;
	}


}
