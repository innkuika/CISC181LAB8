package pkgCore;

import pkgEnum.eBetAction;

public abstract class Bet {
	private int BetAmount;
	private eBetAction eBT;
	
	public Bet(int Amount, eBetAction ebt)
	{
		this.BetAmount = Amount;
		this.eBT = eBT;
	}

	public int getBetAmount() {
		return BetAmount;
	}

	public eBetAction geteBT() {
		return eBT;
	}		
}
