package pkgBetStrategy;

import pkgEnum.eBetType;
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
	private eBetType eBetType;
	public double getiBetMinPct() {
		return iBetMinPct;
	}
	public void setiBetMinPct(double iBetMinPct) {
		this.iBetMinPct = iBetMinPct;
	}
	public double getiBetMaxPct() {
		return iBetMaxPct;
	}
	public void setiBetMaxPct(double iBetMaxPct) {
		this.iBetMaxPct = iBetMaxPct;
	}
	public eBetType geteBetType() {
		return eBetType;
	}
	public void seteBetType(eBetType eBetType) {
		this.eBetType = eBetType;
	}

	
}
