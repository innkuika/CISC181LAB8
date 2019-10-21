package pkgCore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import pkgEnum.eCardCount;
import pkgEnum.eCardDestination;
import pkgEnum.eCardVisibility;
import pkgEnum.eGame;
import pkgEnum.eRank;
import pkgEnum.eSuit;
import pkgException.DeckException;
import pkgException.HandException;
import pkgHelper.GamePlayHelper;
import pkgHelper.HandPokerHelper;

public class TexasHoldemTest {

 
	@Test
	public void GenerateHands() throws DeckException, HandException {
		Table t = new Table("my table");
		Player p1 = new Player("Bert");
		Player p2 = new Player("Joe");
		Player p3 = new Player("Jim");
		
		t.AddPlayerToTable(p1);
		t.AddPlayerToTable(p2);
		t.AddPlayerToTable(p3);
		
		Rule rle = new Rule(eGame.TexasHoldEm);
		GamePlay gp = new GamePlay(t, rle);
		gp.StartGame();
		
		//	P1 Cards
		ArrayList<Card> p1Cards = new ArrayList<Card>();
		p1Cards.add(new Card(eSuit.CLUBS, eRank.TWO));
		p1Cards.add(new Card(eSuit.DIAMONDS, eRank.THREE));
		
		HandPoker hp1 = HandPokerHelper.SetHand(p1Cards, gp.GetPlayersHand(p1));
		GamePlayHelper.PutGamePlay(gp, p1.getPlayerID(), hp1);
		
		//	Common Cards
		ArrayList<Card> commonCards = new ArrayList<Card>();
		commonCards.add(new Card(eSuit.SPADES, eRank.FOUR));
		commonCards.add(new Card(eSuit.SPADES, eRank.FIVE));
		commonCards.add(new Card(eSuit.SPADES, eRank.SIX));
		commonCards.add(new Card(eSuit.DIAMONDS, eRank.ACE));
		//commonCards.add(new Card(eSuit.SPADES, eRank.QUEEN));
		GamePlayHelper.setCommonCards(gp,  commonCards);
		
		//	Evaluate
		gp.EvaluateGameHands();
		
		
		//	Get P1 Hand
		hp1 = gp.GetPlayersHand(p1);
		
		PrintHand(gp.GetPlayersHand(p1).getCards(),"Player 1 Cards");
		PrintHand(gp.getCommonCards(),"Common Cards");
		
		ArrayList<HandPoker> BestPossibleHands = hp1.getGP().getBestPossibleHands(p1);
		System.out.println(BestPossibleHands.size());
		
		ArrayList<HandPoker> BestMadeHands = hp1.getGP().getBestMadeHands(p1);
		System.out.println(BestMadeHands.size());

		
		for (HandPoker hp: BestPossibleHands)
		{
			this.PrintHand(hp.getCards(), hp.getHandScorePoker().geteHandStrength().toString());
			//System.out.println(hp.getHandScorePoker().geteHandStrength());
		}
		
		
		
//		CardDraw cd1 = new CardDraw(eCardCount.Three, eCardDestination.COMMON, eCardVisibility.EVERYONE);
//		gp.Draw(null, cd1);
//		gp.EvaluateGameHands();
//		
//		CardDraw cd2 = new CardDraw(eCardCount.One, eCardDestination.COMMON, eCardVisibility.EVERYONE);
//		gp.Draw(null, cd2);
//		gp.EvaluateGameHands();
//		
//		//CardDraw cd3 = new CardDraw(eCardCount.One, eCardDestination.COMMON, eCardVisibility.EVERYONE);
//		//gp.Draw(null, cd3);
//		
//		//gp.EvaluateGameHands();
//
//		
//		HandPoker hp1 = gp.GetPlayersHand(p1);
//		HandPoker hp2 = gp.GetPlayersHand(p2);
//		HandPoker hp3 = gp.GetPlayersHand(p3);
//		/*
//		try {
//			hp1 = hp1.EvaluateHand(hp1);
//			hp2 = hp2.EvaluateHand(hp2);
//			hp3 = hp3.EvaluateHand(hp3);
//		} catch (HandException e) {
//
//			e.printStackTrace();
//		}
//*/
//	
//		PrintHand(gp.GetPlayersHand(p1).getCards(),"Player 1 Cards");
//		PrintHand(gp.GetPlayersHand(p2).getCards(),"Player 2 Cards");
//		PrintHand(gp.GetPlayersHand(p3).getCards(),"Player 3 Cards");
//		
//		PrintHand(gp.getCommonCards(),"Common Cards");
//		
//		ArrayList<HandPoker> BestPossibleHands = hp1.getGP().getBestPossibleHands(p1);
//		//assertEquals(0,BestPossibleHands.size());
//		
//		ArrayList<HandPoker> BestMadeHands = hp1.getGP().getBestMadeHands(p1);
//		//assertEquals(21,BestMadeHands.size());
//		
//		PrintHand(BestMadeHands.get(0).getCards(), "Best Possible Hand");
//		
//		HashSet<HandScorePoker> setHSP = gp.getUniqueHSP(p1);
//		System.out.println("Unique HandScorePoker count " + setHSP.size());	
//		
//		
//		for (HandPoker hpPossible: BestPossibleHands)
//		{
//			System.out.println(hpPossible.getHandScorePoker().geteHandStrength());
//		}
		
	}

	
	public void PrintHand(ArrayList<Card> cards, String strHandName)
	{
		System.out.println("************" + strHandName + "************" );
		for (Card handPC : cards)
		{
			System.out.print(handPC.geteRank() + " " + handPC.geteSuit() + " " + handPC.geteSubstituteDeck() + " " );
		}
		System.out.println(" ");
	}
	
}
