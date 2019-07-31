package pkgBettingStrategy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import pkgBetStrategy.BetAmount;
import pkgBetStrategy.BettingStrategy;
import pkgBetStrategy.BettingStrategyPreFlop;
import pkgCore.Card;
import pkgEnum.eBetAction;
import pkgEnum.eBetRound;
import pkgEnum.eRank;
import pkgEnum.eSuit;

public class BettingStrategyTest {

	@Test
	public void LoadBettingStrategyTest_1() {
		int iBetPositionNbr = 1;
		eBetRound eBR = eBetRound.PREFLOP;
		Card c1 = new Card(eSuit.CLUBS, eRank.SEVEN);
		Card c2 = new Card(eSuit.SPADES, eRank.SEVEN);

		BettingStrategy bs = null;
		try {
			bs = BettingStrategyPreFlop
					.getBettingStrategy(iBetPositionNbr, 
							eBR, 
							c1, 
							c2, 
							0, 
							100, 
							1000);
		} catch (SAXException e) {
			e.printStackTrace();
			fail("Failed on XSD validation");
		}


		assertNotNull(bs);
		
		assertEquals(eBetAction.CHECK, bs.geteBetPlayerAction());
		
	}
	
	
	@Test
	public void LoadBettingStrategyTest_2() {
		int iBetRoundNbr = 1;
		int iBetPositionNbr = 1;
		eBetRound eBR = eBetRound.PREFLOP;
		Card c1 = new Card(eSuit.CLUBS, eRank.SEVEN);
		Card c2 = new Card(eSuit.SPADES, eRank.SEVEN);
		int iCurrentBetAmt = 5;
		int iCurrentPotAmt = 100;
		int iCurrentStakeAmt = 1000;
		
		int iMinBetAmt = 10;
		int iMaxBetAmt = 15;


		BettingStrategy bs = null;
		try {
			bs = BettingStrategyPreFlop
					.getBettingStrategy(iBetPositionNbr, 
							eBR, 
							c1, 
							c2,
							iCurrentBetAmt,
							iCurrentPotAmt,
							iCurrentStakeAmt);
		} catch (SAXException e) {
			e.printStackTrace();
			fail("Failed on XSD validation");
		}

		assertNotNull(bs);		
		assertEquals(eBetAction.RAISE, bs.geteBetPlayerAction());	
		
		assertTrue(iMinBetAmt <= bs.getBetAmount().getBetAmt());
		assertTrue(iMaxBetAmt >= bs.getBetAmount().getBetAmt());
		System.out.println(bs.getBetAmount().getBetAmt());			
	}

	@Test
	public void LoadBettingStrategyTest_3() {
		int iBetRoundNbr = 1;
		int iBetPositionNbr = 1;
		eBetRound eBR = eBetRound.PREFLOP;
		Card c1 = new Card(eSuit.CLUBS, eRank.SEVEN);
		Card c2 = new Card(eSuit.SPADES, eRank.SEVEN);
		int iCurrentBetAmt = 50;
		int iCurrentPotAmt = 100;
		int iCurrentStakeAmt = 1000;
		
		int iMinBetAmt = 10;
		int iMaxBetAmt = 15;


		BettingStrategy bs = null;
		try {
			bs = BettingStrategyPreFlop
					.getBettingStrategy( 
							iBetPositionNbr, 
							eBR, 
							c1, 
							c2,
							iCurrentBetAmt,
							iCurrentPotAmt,
							iCurrentStakeAmt);
		} catch (SAXException e) {			
			e.printStackTrace();
			fail("Failed on XSD validation");
		}

		assertNotNull(bs);		
		assertEquals(eBetAction.FOLD, bs.geteBetPlayerAction());	
	}
	

}
