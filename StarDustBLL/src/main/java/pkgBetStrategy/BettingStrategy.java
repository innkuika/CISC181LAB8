package pkgBetStrategy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.PROPERTY)
public class BettingStrategy {

	@XmlElement
	private int StrategyNbr;
	@XmlElement
	private String Card1Rank;
	@XmlElement
	private String Card2Rank;
	@XmlElement
	private boolean isSameSuit;
	@XmlElement
	private boolean isSameRank;
	@XmlElement
	private String BetAction;
	@XmlElement
	private BetAmount BetAmount;		
}
