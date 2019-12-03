package pkgCore;

import java.io.Serializable;
import java.util.ArrayList;

public class HandScorePokerSummary implements Serializable {

	private ArrayList<HandPoker> BestMadeHands;
	private ArrayList<HandPoker> BestPossibleHands;
	public HandScorePokerSummary(ArrayList<HandPoker> bestMadeHands, ArrayList<HandPoker> bestPossibleHands) {
		super();
		BestMadeHands = bestMadeHands;
		BestPossibleHands = bestPossibleHands;		
	}
	public ArrayList<HandPoker> getBestMadeHands() {
		return BestMadeHands;
	}
	public ArrayList<HandPoker> getBestPossibleHands() {
		return BestPossibleHands;
	}	
}
