package pkgBetStrategy;

import java.util.Collections;

import pkgCore.Card;
import pkgEnum.eBetAction;
import pkgEnum.eBetRound;

public class BettingStrategyPreFlop extends BettingStrategy {

	/**
	 * getBettingStrategy - return a single BettingStrategy for a given BetRoundNbr,
	 * BetPositionNbr, eBetRound and two Cards
	 * 
	 * If no BetStrategy is found, return null
	 * 
	 * @param BetRoundNbr
	 * @param BetPositionNbr
	 * @param eBR
	 * @param c1
	 * @param c2
	 * @return
	 * @throws Exception 
	 */
	public static BettingStrategy getBettingStrategy(int BetPositionNbr, eBetRound eBR, Card c1, Card c2,
			int iCurrentBetAmt, int iCurrentPotAmt, int iCurrentStakeAmt) throws Exception {

		BettingStrategy bsFound = null;
		eBetAction eCurrentBetAction = (iCurrentBetAmt > 0) ? eBetAction.BET : eBetAction.CHECK;

		// This code will ensure c1 is the higher rank.
		if (c1.geteRank().getiRankNbr() < c2.geteRank().getiRankNbr()) {
			Card c0 = c1;
			c1 = c2;
			c2 = c0;
			c0 = null;
		}

		//	set bSameSuit, bSameRank variables.
		boolean bSameSuit = (c1.geteSuit().equals(c2.geteSuit()) ? true : false);
		boolean bSameRank = (c1.geteRank().equals(c2.geteRank()) ? true : false);

		//	Loop through the betting strategies
		//		If you find a strategy where both cards are >= the strategy and..
		//		the bSameRank and...
		//		the bSameSuit rules are equal, use that strategy.
		
		for (BettingStrategy bs : getBettingStrategy(BetPositionNbr, eBR)) {
			Collections.sort(bs.getCardRanks());
			
			if ((c1.geteRank().getiRankNbr() >= bs.getCardRanks().get(0).getiRankNbr())
					&& (c2.geteRank().getiRankNbr() >= bs.getCardRanks().get(1).getiRankNbr()) && (bSameSuit == bs.isSameSuit())
					&& (bSameRank == bs.isSameRank())) {

				bsFound = bs;
				bsFound.seteBetPlayerAction(bs.geteBetCurrentAction());

				//	If the currentBetAction = calculated betaction, then break to get out of for loop
				if ((c1.geteRank().getiRankNbr() == bs.getCardRanks().get(0).getiRankNbr())
						&& (c2.geteRank().getiRankNbr() == bs.getCardRanks().get(1).getiRankNbr())
						&& (eCurrentBetAction == bs.geteBetCurrentAction())) {
					break;
				}
			}
		}

		//	A strategy is found, figure the bet amount
		if (bsFound != null) {
			// If the player's action is a check, don't set the values for
			// bet amon, return
			if (bsFound.geteBetPlayerAction() == eBetAction.CHECK)
				return bsFound;

			BetAmount ba = bsFound.getBetAmount().DetermineBet(bsFound, iCurrentBetAmt, iCurrentPotAmt,
					iCurrentStakeAmt);

			//	If the bet amount can't be determined, fold the hand
			if (ba == null) {
				bsFound.seteBetPlayerAction(eBetAction.FOLD);
			} else {
				if (ba.getBetAmt() == iCurrentBetAmt) {
					//	If the bet amount is the same thing as the current bet, it's a 'call'.
					bsFound.seteBetPlayerAction(eBetAction.CALL);
				}
				else if (ba.getBetAmt() > iCurrentBetAmt)
				{
					bsFound.seteBetPlayerAction(eBetAction.RAISE);
				}
				bsFound.setBetAmount(ba);
			}
		}

		//	Return the strategy found.  If this is null, there was no strategy found.
		return bsFound;
	}
}
