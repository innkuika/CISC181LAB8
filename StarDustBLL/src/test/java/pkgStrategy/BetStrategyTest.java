package pkgStrategy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pkgBetStrategy.BettingStrategy;
import pkgBetStrategy.BettingStrategyPreFlop;
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

		BettingStrategy bs = null;
		try {
			bs = BettingStrategyPreFlop.getBettingStrategy(BetPositionNbr, eBR, c1, c2, iCurrentBetAmt, iCurrentPotAmt,
					iCurrentStakeAmt);
		} catch (Exception e) {
			e.printStackTrace();
			fail("BetStrategy_Seven_Check failed");
		}

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
		int iMinExpectedBet = (int) (iCurrentPotAmt * .10);
		int iMaxExpectedBet = (int) (iCurrentPotAmt * .15);

		BettingStrategy bs = null;
		try {
			bs = BettingStrategyPreFlop.getBettingStrategy(BetPositionNbr, eBR, c1, c2, iCurrentBetAmt, iCurrentPotAmt,
					iCurrentStakeAmt);
		} catch (Exception e) {
			e.printStackTrace();
			fail("BetStrategy_Seven_Bet failed");
		}

		assertNotNull(bs);
		assertEquals(eBetAction.RAISE, bs.geteBetPlayerAction());

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
		int iMinExpectedBet = (int) (iCurrentPotAmt * .10);
		int iMaxExpectedBet = (int) (iCurrentPotAmt * .15);

		BettingStrategy bs = null;
		try {
			bs = BettingStrategyPreFlop.getBettingStrategy(BetPositionNbr, eBR, c1, c2, iCurrentBetAmt, iCurrentPotAmt,
					iCurrentStakeAmt);
		} catch (Exception e) {
			e.printStackTrace();
			fail("BetStrategy_Seven_Fold failed");
		}

		assertNotNull(bs);
		assertEquals(eBetAction.FOLD, bs.geteBetPlayerAction());
		assertEquals(0, bs.getBetAmount().getBetAmt());

	}

	@Test
	public void BetStrategy_Eight_Check() {

		int BetPositionNbr = 1;
		eBetRound eBR = eBetRound.PREFLOP;
		Card c1 = new Card(eSuit.HEARTS, eRank.EIGHT);
		Card c2 = new Card(eSuit.CLUBS, eRank.EIGHT);
		int iCurrentBetAmt = 0;
		int iCurrentPotAmt = 100;
		int iCurrentStakeAmt = 1000;
		int iMinExpectedBet = (int) (iCurrentPotAmt * .10);
		int iMaxExpectedBet = (int) (iCurrentPotAmt * .15);

		BettingStrategy bs = null;
		try {
			bs = BettingStrategyPreFlop.getBettingStrategy(BetPositionNbr, eBR, c1, c2, iCurrentBetAmt, iCurrentPotAmt,
					iCurrentStakeAmt);
		} catch (Exception e) {
			e.printStackTrace();
			fail("BetStrategy_Eight_Check failed");
		}

		assertNotNull(bs);
		assertEquals(eBetAction.CHECK, bs.geteBetPlayerAction());
		assertEquals(0, bs.getBetAmount().getBetAmt());

	}

	@Test
	public void BetStrategy_Eight_Call() {

		int BetPositionNbr = 1;
		eBetRound eBR = eBetRound.PREFLOP;
		Card c1 = new Card(eSuit.HEARTS, eRank.EIGHT);
		Card c2 = new Card(eSuit.CLUBS, eRank.EIGHT);
		int iCurrentBetAmt = 350;
		int iCurrentPotAmt = 100;
		int iCurrentStakeAmt = 1000;
		int iMinExpectedBet = (int) (iCurrentPotAmt * .10);
		int iMaxExpectedBet = (int) (iCurrentPotAmt * .15);

		BettingStrategy bs = null;
		try {
			bs = BettingStrategyPreFlop.getBettingStrategy(BetPositionNbr, eBR, c1, c2, iCurrentBetAmt, iCurrentPotAmt,
					iCurrentStakeAmt);
		} catch (Exception e) {
			e.printStackTrace();
			fail("BetStrategy_Eight_Call failed");
		}

		assertNotNull(bs);
		assertEquals(eBetAction.CALL, bs.geteBetPlayerAction());
		assertEquals(350, bs.getBetAmount().getBetAmt());

	}
}
