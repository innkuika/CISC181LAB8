package pkgBetStrategy;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerPosition {

	@XmlAttribute
	private int BetPosition;
 
	@XmlElement
	private LinkedList<BettingStrategy> BettingStrategy = new LinkedList<BettingStrategy>();

	public int getBetPosition() {
		return BetPosition;
	}


	public void setBetPosition(int betPosition) {
		BetPosition = betPosition;
	}


	public LinkedList<BettingStrategy> getBettingStrategy() {
		return BettingStrategy;
	}


	public void setBettingStrategy(LinkedList<BettingStrategy> bettingStrategy) {
		BettingStrategy = bettingStrategy;
	}
	
	public void addBettingStrategy(BettingStrategy bettingStrategy)
	{
		BettingStrategy.add(bettingStrategy);
	}
	
	
	
	
}
