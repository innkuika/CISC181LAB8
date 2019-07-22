package pkgBetStrategy;

import javax.xml.bind.annotation.XmlAttribute;

public class BettingStrategy {

	@XmlAttribute
	private int StrategyNbr;
	@XmlAttribute
	private String strCard1Rank;
	@XmlAttribute
	private String strCard2Rank;
	@XmlAttribute
	private boolean isSameSuit;
	@XmlAttribute
	private boolean isSameRank;
	@XmlAttribute
	private String BetAction;
	@XmlAttribute
	private double BetAmount;
	
}
