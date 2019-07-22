package pkgBetStrategy;

import java.util.ArrayList;
import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayersBeforeBet {

	@XmlAttribute
	private ArrayList<BetterAhead> BettersAhead;
}
