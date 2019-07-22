package pkgBet;

import pkgEnum.eBetAction;
import pkgEnum.eBetRound;

public abstract class Bet {
	
	private int BetAmount;
	private int iBetPosition;
	private int iPositionCnt;
	private eBetAction eBT;
	private eBetRound eBR;
	
	public Bet(int iBetPosition, int iPositionCnt, eBetAction ebt, eBetRound ebr)
	{
		this.eBT = eBT;
		this.eBR = ebr;
		this.iBetPosition = iBetPosition;
		this.iPositionCnt = iPositionCnt;
	}

	public int getBetAmount() {
		return BetAmount;
	}

	public eBetAction geteBT() {
		return eBT;
	}

	public eBetRound geteBR() {
		return eBR;
	}

	public int getiBetPosition() {
		return iBetPosition;
	}

	public int getiPositionCnt() {
		return iPositionCnt;
	}

	protected void setBetAmount(int betAmount) {
		BetAmount = betAmount;
	}		
	
	
	
	
}
