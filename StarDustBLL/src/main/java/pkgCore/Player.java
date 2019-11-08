package pkgCore;

import java.io.Serializable;
import java.util.UUID;

public class Player implements Serializable {

	private UUID PlayerID;
	private String PlayerName;
	private int ClientID;
	private int iPlayerPosition;
	
	
	 
	public int getiPlayerPosition() {
		return iPlayerPosition;
	}

	public void setiPlayerPosition(int iPlayerPosition) {
		this.iPlayerPosition = iPlayerPosition;
	}

	public Player(String playerName, int iClientID) {		
		super();
		PlayerID = UUID.randomUUID();
		PlayerName = playerName;
		this.ClientID = iClientID;
	}
		
	public Player(String playerName) {
		this.PlayerID = UUID.randomUUID();
		PlayerName = playerName;
	}

	public String getPlayerName() {
		return PlayerName;
	}

	public void setPlayerName(String playerName) {
		PlayerName = playerName;
	}

	public UUID getPlayerID() {
		return PlayerID;
	}

	public int getClientID() {
		return ClientID;
	}


	
	
	
	
}
