package pkgCore;

import java.util.Objects;

public class PlayerHand {

	private Player player;
	private Hand hand;
	public PlayerHand(Player player, Hand hand) {
		super();
		this.player = player;
		this.hand = hand;
	}
	public Player getPlayer() {
		return player;
	}
	public Hand getHand() {
		return hand;
	}
	@Override
	public int hashCode() {
		return Objects.hash(player.getPlayerID(), hand.getHandID());
	}

	
	
}
