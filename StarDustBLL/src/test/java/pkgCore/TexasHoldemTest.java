package pkgCore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import pkgEnum.eCardCount;
import pkgEnum.eCardDestination;
import pkgEnum.eCardVisibility;
import pkgEnum.eGame;
import pkgEnum.eRank;
import pkgEnum.eSuit;
import pkgException.DeckException;
import pkgException.HandException;

class TexasHoldemTest {

	@Test
	void GenerateHands() throws DeckException {
		Table t = new Table("my table");
		Player p1 = new Player("Bert");
		Player p2 = new Player("Joe");
		t.AddPlayerToTable(p1);
		t.AddPlayerToTable(p2);
		
		Rule rle = new Rule(eGame.TexasHoldEm);
		GamePlay gp = new GamePlay(t, rle);
		gp.StartGame();

		CardDraw cd1 = new CardDraw(eCardCount.Three, eCardDestination.COMMON, eCardVisibility.EVERYONE);
		gp.Draw(null, cd1);
		/*
		CardDraw cd2 = new CardDraw(eCardCount.One, eCardDestination.COMMON, eCardVisibility.EVERYONE);
		gp.Draw(null, cd2);
		*/
		HandPoker hp = gp.GetPlayersHand(p1);
		
		/*
		for (Card c : hp.getCards())
		{
			System.out.print(c.geteRank());
			System.out.print(" " );
			System.out.println(c.geteSuit());
		}
		System.out.println(" ");
		for (Card c: gp.getCommonCards())
		{
			System.out.print(c.geteRank());
			System.out.print(" " );
			System.out.println(c.geteSuit());
		}
		*/
		ArrayList<HandPoker> CombinationHands = null;
		try {
			CombinationHands = hp.GetTexasHoldemHands();
		} catch (HandException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(21,CombinationHands.size());
		

		
		for (HandPoker handP : CombinationHands)
		{
			//PrintHand(handP.getCards(), "Combination Hands");
		}
		
		PrintHand(gp.GetPlayersHand(p1).getCards(),"Player 1 Cards");
		PrintHand(gp.getCommonCards(),"Common Cards");
		HandPoker BestPossibleHand = hp.FindBestHand(CombinationHands, false);
		PrintHand(BestPossibleHand.getCards(), "Best Possible Hand");
		HandPoker BestMadeHand = hp.FindBestHand(CombinationHands, true);
		PrintHand(BestMadeHand.getCards(), "Best Made Hand");
	}

	
	public void PrintHand(ArrayList<Card> cards, String strHandName)
	{
		System.out.println("************" + strHandName + "************" );
		for (Card handPC : cards)
		{
			System.out.print(handPC.geteRank() + " " + handPC.geteSuit() + " ");
		}
		System.out.println(" ");
	}
	
}
