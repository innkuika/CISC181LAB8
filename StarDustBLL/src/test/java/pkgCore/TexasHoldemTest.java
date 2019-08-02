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

public class TexasHoldemTest {


	@Test
	public void GenerateHands() throws DeckException {
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
		 
		CardDraw cd2 = new CardDraw(eCardCount.One, eCardDestination.COMMON, eCardVisibility.EVERYONE);
		gp.Draw(null, cd2);
	 

		
		HandPoker hp1 = gp.GetPlayersHand(p1);
		HandPoker hp2 = gp.GetPlayersHand(p2);

		try {
			hp1 = hp1.EvaluateHand(hp1);
			hp2 = hp1.EvaluateHand(hp2);
		} catch (HandException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		PrintHand(gp.GetPlayersHand(p1).getCards(),"Player 1 Cards");
		PrintHand(gp.GetPlayersHand(p2).getCards(),"Player 2 Cards");
		
		PrintHand(gp.getCommonCards(),"Common Cards");
		HandPoker BestPossibleHand = hp1.getGP().getBestPossibleHand(p1);
		
		/*
		PrintHand(BestPossibleHand.getCards(), "Best Possible Hand");
		*/
				
		PrintHand(hp1.getGP().getBestMadeHand(p1).getCards(), "Best Made Hand Player 1");
		PrintHand(hp1.getGP().getBestMadeHand(p2).getCards(), "Best Made Hand Player 2");

		/*
		if (BestPossibleHand.equals(hp.getGP().getBestMadeHand(p1).getCards()))
		{
			System.out.println("You have the best possible hand");
		}
		*/
		
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
