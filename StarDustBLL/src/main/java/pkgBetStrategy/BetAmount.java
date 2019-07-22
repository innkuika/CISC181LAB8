package pkgBetStrategy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class BetAmount {

	@XmlElement
	private double iBetMinPct;
	@XmlElement
	private double iBetMaxPct;
	@XmlElement
	private String strBetPotOrStake;
	
}
