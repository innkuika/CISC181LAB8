package pkgStrategy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pkgBetStrategy.BettingStrategy;
import pkgCore.Card;
import pkgEnum.eBetAction;
import pkgEnum.eBetRound;
import pkgEnum.eRank;
import pkgEnum.eSuit;

public class BetStrategyTest {

	@Test
	public void BetStrategy_Seven_Check() {

		int BetPositionNbr = 1;
		eBetRound eBR = eBetRound.PREFLOP;
		Card c1 = new Card(eSuit.HEARTS, eRank.SEVEN);
		Card c2 = new Card(eSuit.CLUBS, eRank.SEVEN);
		int iCurrentBetAmt = 0;
		int iCurrentPotAmt = 100;
		int iCurrentStakeAmt = 1000;
		
		BettingStrategy bs = BettingStrategy
				.getBettingStrategy(BetPositionNbr, 
						eBR, 
						c1, 
						c2, 
						iCurrentBetAmt, 
						iCurrentPotAmt, 
						iCurrentStakeAmt);
		
		assertNotNull(bs);
		assertEquals(eBetAction.CHECK, bs.geteBetPlayerAction());		
	}
	
	@Test
	public void BetStrategy_Seven_Bet() {

		int BetPositionNbr = 1;
		eBetRound eBR = eBetRound.PREFLOP;
		Card c1 = new Card(eSuit.HEARTS, eRank.SEVEN);
		Card c2 = new Card(eSuit.CLUBS, eRank.SEVEN);
		int iCurrentBetAmt = 5;
		int iCurrentPotAmt = 100;
		int iCurrentStakeAmt = 1000;
		int iMinExpectedBet = (int)(iCurrentPotAmt * .10);
		int iMaxExpectedBet = (int)(iCurrentPotAmt * .15);
		
		BettingStrategy bs = BettingStrategy
				.getBettingStrategy(BetPositionNbr, 
						eBR, 
						c1, 
						c2, 
						iCurrentBetAmt, 
						iCurrentPotAmt, 
						iCurrentStakeAmt);
		
		assertNotNull(bs);
		assertEquals(eBetAction.BET, bs.geteBetPlayerAction());		
		
		assertTrue(iMinExpectedBet <= bs.getBetAmount().getBetAmt());
		assertTrue(iMaxExpectedBet >= bs.getBetAmount().getBetAmt());
	}	

	@Test
	public void BetStrategy_Seven_Fold() {

		int BetPositionNbr = 1;
		eBetRound eBR = eBetRound.PREFLOP;
		Card c1 = new Card(eSuit.HEARTS, eRank.SEVEN);
		Card c2 = new Card(eSuit.CLUBS, eRank.SEVEN);
		int iCurrentBetAmt = 50;
		int iCurrentPotAmt = 100;
		int iCurrentStakeAmt = 1000;
		int iMinExpectedBet = (int)(iCurrentPotAmt * .10);
		int iMaxExpectedBet = (int)(iCurrentPotAmt * .15);
		
		BettingStrategy bs = BettingStrategy
				.getBettingStrategy(BetPositionNbr, 
						eBR, 
						c1, 
						c2, 
						iCurrentBetAmt, 
						iCurrentPotAmt, 
						iCurrentStakeAmt);
		
		assertNotNull(bs);
		assertEquals(eBetAction.FOLD, bs.geteBetPlayerAction());		
		assertEquals(0,bs.getBetAmount().getBetAmt());

	}	
}
