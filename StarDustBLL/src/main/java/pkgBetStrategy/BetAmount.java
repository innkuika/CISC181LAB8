package pkgBetStrategy;

import pkgEnum.eBetType;

import java.security.SecureRandom;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import pkgEnum.eBetType;

@XmlAccessorType(XmlAccessType.FIELD)
public class BetAmount {

	@XmlElement
	private double iBetMinPct;
	@XmlElement
	private double iBetMaxPct;
	@XmlElement
	private eBetType eBetType;
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
	
	public static int GetBetAmount(BettingStrategy bs, int PotAmount, int StakeAmount)
	{
		BetAmount ba = bs.getBetAmount();
		double BetMinPct = ba.getiBetMinPct();
		double BetMaxPct = ba.getiBetMaxPct();
		int iBetMinAmount = 0;
		int iBetMaxAmount = 0;
		
		int BetAmt = 0;
		SecureRandom random = new SecureRandom();
		
		if (ba.geteBetType() == pkgEnum.eBetType.POT)
		{
			iBetMinAmount = (int) (PotAmount * (BetMinPct * 0.01));
			iBetMaxAmount = (int) (PotAmount * (BetMaxPct * 0.01));
		}
		else if (ba.geteBetType() == pkgEnum.eBetType.STAKE)
		{
			iBetMinAmount = (int) (StakeAmount * BetMinPct);
			iBetMaxAmount = (int) (StakeAmount * BetMaxPct);
		}
		BetAmt =(random.nextInt(iBetMaxAmount -iBetMinAmount+1)+iBetMinAmount);
		return BetAmt;
	}

	
}
