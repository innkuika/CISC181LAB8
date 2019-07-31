package pkgCore;

import pkgEnum.eCardCount;
import pkgEnum.eCardDestination;
import pkgEnum.eCardNo;
import pkgEnum.eCardVisibility;

public class CardDraw {

	private eCardCount CardCount;
	private eCardDestination CardDestination;
	private eCardVisibility CardVisibility;

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
