package pkgBetStrategy;

import java.util.LinkedList;

import javax.xml.bind.annotation.XmlAttribute;

public class BetterAhead {

	@XmlAttribute
	private int BetPosition;
	
	@XmlAttribute
	private LinkedList<BettingStrategy> bettingStrategy = new LinkedList<BettingStrategy>();
}
