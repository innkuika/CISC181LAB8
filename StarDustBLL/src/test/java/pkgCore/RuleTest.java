package pkgCore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pkgEnum.eCardDestination;
import pkgEnum.eDrawCount;
import pkgEnum.eGame;
import pkgEnum.eStartEnd;

public class RuleTest {

	@Test
	public void RuleTest() {

		Rule rle = new Rule(eGame.TexasHoldEm);
		int iStartIdx = rle.getIdx(eDrawCount.FOURTH,  eStartEnd.START);
		int iEndIdx = rle.getIdx(eDrawCount.FOURTH,  eStartEnd.END);
		

	}

}
