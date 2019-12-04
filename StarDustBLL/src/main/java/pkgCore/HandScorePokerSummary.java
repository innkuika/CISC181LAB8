package pkgCore;

import java.io.Serializable;
import java.util.ArrayList;

public class HandScorePokerSummary implements Serializable {

	private ArrayList<HandPoker> BestMadeHands;
	private ArrayList<HandPoker> BestPossibleHands;
	private Player iPlayer;
	
	public Player getiPlayer() {
		return iPlayer;
	}
	public HandScorePokerSummary(ArrayList<HandPoker> bestMadeHands, ArrayList<HandPoker> bestPossibleHands,Player p) {
		super();
		BestMadeHands = bestMadeHands;
		BestPossibleHands = bestPossibleHands;
		iPlayer = p;
	}
	public ArrayList<HandPoker> getBestMadeHands() {
		return BestMadeHands;
	}
	public ArrayList<HandPoker> getBestPossibleHands() {
		return BestPossibleHands;
	}	
}
