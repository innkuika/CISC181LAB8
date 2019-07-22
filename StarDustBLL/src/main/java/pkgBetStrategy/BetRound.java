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
	private String strBetRound;
	
	@XmlTransient
	private eBetRound ebr;
 
	@XmlElement
	private ArrayList<PlayerPosition> PlayerPosition;
 
}
