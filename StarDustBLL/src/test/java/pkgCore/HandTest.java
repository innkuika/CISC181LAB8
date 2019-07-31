package pkgCore;

import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import pkgEnum.eCardNo;
import pkgEnum.eHandStrength;
import pkgEnum.eRank;
import pkgEnum.eSuit;
import pkgException.HandException;

class HandTest {

	private HandPoker SetHand(ArrayList<Card> setCards, HandPoker h) {
		Object t = null;

		try {
			// Load the Class into 'c'
			Class<?> c = Class.forName("pkgCore.HandPoker");
			// Create a new instance 't' from the no-arg Deck constructor
			t = c.newInstance();
			// Load 'msetCardsInHand' with the 'Draw' method (no args);
			Method msetCardsInHand = c.getDeclaredMethod("setCards", new Class[] { ArrayList.class });
			// Change the visibility of 'setCardsInHand' to true *Good Grief!*
			msetCardsInHand.setAccessible(true);
			// invoke 'msetCardsInHand'
			Object oDraw = msetCardsInHand.invoke(t, setCards);

		} catch (ClassNotFoundException x) {
			x.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		h = (HandPoker) t;
		return h;

	}

	public boolean EvaluateHandScoreMethod(HandPoker hp, String strHandStrength) {
		Class c = hp.getClass();
		boolean bReturn = false;
		try {
			Method mFreq = c.getDeclaredMethod("Frequency", null);
			mFreq.setAccessible(true);
			mFreq.invoke(hp, null);
			
			Method msetCardsInHand = c.getDeclaredMethod(strHandStrength, null);
			msetCardsInHand.setAccessible(true);
			bReturn = (boolean)msetCardsInHand.invoke(hp, null);

		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bReturn;
	}

	@Test
	public void TestFourOfAKind1() {

		HandScorePoker hsp = new HandScorePoker();
		ArrayList<Card> FourOfAKind = new ArrayList<Card>();
		FourOfAKind.add(new Card(eSuit.CLUBS, eRank.ACE));
		FourOfAKind.add(new Card(eSuit.CLUBS, eRank.ACE));
		FourOfAKind.add(new Card(eSuit.CLUBS, eRank.ACE));
		FourOfAKind.add(new Card(eSuit.CLUBS, eRank.ACE));
		FourOfAKind.add(new Card(eSuit.CLUBS, eRank.KING));
		Collections.sort(FourOfAKind);
		HandPoker h = new HandPoker();
		h = SetHand(FourOfAKind, h);
		assertTrue(EvaluateHandScoreMethod(h,"isFourOfAKind"));
		
		HandScorePoker HSP = h.getHandScorePoker();
		
		assertEquals(eHandStrength.FourOfAKind, HSP.geteHandStrength());
		assertEquals(eRank.ACE, HSP.getHiCard().geteRank());
		
		assertEquals(HSP.getKickers().get(eCardNo.FIRST.getiCardNo()).geteRank(),
				  eRank.KING);

	}
	
	@Test
	public void TestFourOfAKind_2() {

		HandScorePoker hsp = new HandScorePoker();
		ArrayList<Card> FourOfAKind = new ArrayList<Card>();
		FourOfAKind.add(new Card(eSuit.CLUBS, eRank.ACE));
		FourOfAKind.add(new Card(eSuit.CLUBS, eRank.ACE));
		FourOfAKind.add(new Card(eSuit.CLUBS, eRank.ACE));
		FourOfAKind.add(new Card(eSuit.JOKER, eRank.JOKER));
		FourOfAKind.add(new Card(eSuit.CLUBS, eRank.KING));
		Collections.sort(FourOfAKind);
		HandPoker h = new HandPoker();
		
		h = SetHand(FourOfAKind, h);
		
		try {
			h = h.EvaluateHand();
		} catch (HandException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Card c: h.getCards())
		{
			System.out.print(c.geteRank());
			System.out.print(" ");
			System.out.println(c.geteSuit());
		}
		assertTrue(EvaluateHandScoreMethod(h,"isFourOfAKind"));
		
		HandScorePoker HSP = h.getHandScorePoker();
		
		assertEquals(eHandStrength.FourOfAKind, HSP.geteHandStrength());
		assertEquals(eRank.ACE, HSP.getHiCard().geteRank());
		
		assertEquals(HSP.getKickers().get(eCardNo.FIRST.getiCardNo()).geteRank(),
				  eRank.KING);

	}
	
	
	@Test
	public void TestFullHouse_1() {

		HandScorePoker hsp = new HandScorePoker();
		ArrayList<Card> FullHouse = new ArrayList<Card>();
		FullHouse.add(new Card(eSuit.CLUBS, eRank.ACE));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.ACE));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.KING));
		FullHouse.add(new Card(eSuit.JOKER, eRank.JOKER));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.KING));
		Collections.sort(FullHouse);
		HandPoker h = new HandPoker();
		
		h = SetHand(FullHouse, h);
		
		try {
			h = h.EvaluateHand();
		} catch (HandException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Card c: h.getCards())
		{
			System.out.print(c.geteRank());
			System.out.print(" ");
			System.out.println(c.geteSuit());
		}
		assertTrue(EvaluateHandScoreMethod(h,"isFullHouse"));
		
		HandScorePoker HSP = h.getHandScorePoker();
		
		assertEquals(eHandStrength.FullHouse, HSP.geteHandStrength());
		assertEquals(eRank.ACE, HSP.getHiCard().geteRank());
		assertEquals(eRank.KING, HSP.getLoCard().geteRank());
		
		assertTrue(HSP.getKickers().size() == 0);

	}
}
