package pkgBetStrategy;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.xml.sax.SAXException;

import pkgEnum.eBetRound;

@XmlAccessorType(XmlAccessType.FIELD)

public class BetRound {
	
	@XmlElement
	private int BetRoundNumber;
	
	@XmlElement
	private eBetRound eBetRound;
 
	@XmlElement
	private ArrayList<PlayerPosition> PlayerPosition = new ArrayList<PlayerPosition>();


	public int getBetRoundNumber() {
		return BetRoundNumber;
	}

	public void setBetRoundNumber(int betRoundNumber) {
		BetRoundNumber = betRoundNumber;
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
	
	public static BetRound getBetRound(eBetRound eBR) throws SAXException 
	{
		BetEngine be = BetEngine.LoadBettingEngine();
		BetRound br =  be.getBetRound()
				.stream()
				.filter(x -> x.geteBetRound() == eBR)
				.findAny()
				.orElse(null);		
		return br;

	}
 
	
}
