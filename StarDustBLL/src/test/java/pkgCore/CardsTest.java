package pkgCore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import pkgEnum.eRank;
import pkgEnum.eSuit;

class CardsTest {

	@Test
	void test() {
 
		
		ArrayList<Card> cards = new ArrayList<Card>();
		Card c1 = new Card(eSuit.CLUBS,eRank.TWO);
		Card c2 = new Card(eSuit.CLUBS,eRank.EIGHT);
		Card c3 = new Card(eSuit.DIAMONDS,eRank.SIX);
		
		cards.add(c1);
		cards.add(c2);
		cards.add(c3);
		
		Collections.sort(cards);
		
		assertEquals(cards.get(0).geteRank(),eRank.EIGHT);
		assertEquals(cards.get(1).geteRank(),eRank.SIX);
		assertEquals(cards.get(2).geteRank(),eRank.TWO);
	}

}
