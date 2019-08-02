package pkgCore;

import pkgEnum.eCardCount;
import pkgEnum.eCardDestination;
import pkgEnum.eCardVisibility;

/**
 * CardDraw - The purpose of this class it to keep track of the count,
 * destination and visibility of cards when they are drawn.
 * 
 * @author Dad
 *
 */
public class CardDraw {

	/**
	 * CardCount - How many cards to draw.
	 */
	private eCardCount CardCount;
	
	/**
	 * CardDestination - Where the card belongs- player or common/community
	 */
	private eCardDestination CardDestination;
	
	/**
	 * CardVisibility - How visible is the card?  Can the other player(s) see the card?
	 */
	private eCardVisibility CardVisibility;

	/**
	 * CardDraw - Constructor.  You need all three parameters.
	 * 
	 * @param cardCount
	 * @param cardDestination
	 * @param cardVisiblity
	 */
	public CardDraw(eCardCount cardCount, eCardDestination cardDestination, eCardVisibility cardVisiblity) {
		super();
		CardCount = cardCount;
		CardDestination = cardDestination;
		CardVisibility = CardVisibility;
	}

	public eCardCount getCardCount() {
		return CardCount;
	}

	public eCardDestination getCardDestination() {
		return CardDestination;
	}

	public eCardVisibility getCardVisibility() {
		return CardVisibility;
	}
}
