package pkgBetStrategy;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import pkgEnum.eBetRound;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class BetRound {
	
	@XmlAttribute
	private int iBetRound;
	
	@XmlElement
	private eBetRound eBetRound;
 
	@XmlElement
	private ArrayList<PlayerPosition> PlayerPosition = new ArrayList<PlayerPosition>();

	public int getiBetRound() {
		return iBetRound;
	}

	public void setiBetRound(int iBetRound) {
		this.iBetRound = iBetRound;
	}

	public eBetRound geteBetRound() {
		return eBetRound;
	}

	public void seteBetRound(eBetRound eBetRound) {
		this.eBetRound = eBetRound;
	}

	public ArrayList<PlayerPosition> getPlayerPosition() {
		return PlayerPosition;
	}

	public void setPlayerPosition(ArrayList<PlayerPosition> playerPosition) {
		PlayerPosition = playerPosition;
	}
	
	public void addPlayerPostiion(PlayerPosition playerPosition)
	{
		PlayerPosition.add(playerPosition);
	}
 
	
}
