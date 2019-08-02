package pkgCore;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import pkgException.DeckException;

public class DeckTest {

	private ArrayList<Card> getCards(Deck d) {
		ArrayList<Card> cards = new ArrayList<Card>();
		try {

			// c = structure of class 'Hand'
			Class<?> c = Class.forName("pkgCore.Deck");
			Method mGetCardsInDeck = c.getDeclaredMethod("getCardsInDeck", null);
			mGetCardsInDeck.setAccessible(true);
			cards = (ArrayList<Card>) mGetCardsInDeck.invoke(d, null);

		} catch (ClassNotFoundException x) {
			x.printStackTrace();
		} catch (IllegalAccessException x) {
			x.printStackTrace();
		} catch (NoSuchMethodException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return cards;
	}

	@Test
	public void CreateDeckTest() {
		Deck d = new Deck();
		ArrayList<Card> cards = this.getCards(d);
		assertEquals(52, cards.size());
		assertEquals(52,d.getiDeckCount());

		try {
			Card c = d.Draw();
		} catch (DeckException e) {
			fail("draw failed");
		}
		cards = this.getCards(d);
		assertEquals(51, cards.size());
	}

	@Test
	public void DeckOverdraw() {
		Assertions.assertThrows(DeckException.class, () -> {
			Deck d = new Deck();
			for (int i = 0; i < 60; i++) {
				Card c = d.Draw();
			}
		});

	}

}
