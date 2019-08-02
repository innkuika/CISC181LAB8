package pkgGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pkgCore.GamePlay;
import pkgCore.Player;
import pkgCore.Rule;
import pkgCore.Table;
import pkgEnum.eGame;
import pkgException.DeckException;
import pkgException.HandException;

class GamePlayTest {
 
	@Test
	void GamePlay_Test1() {
		Table t = new Table("Table 1");
		t.AddPlayerToTable(new Player("Bert"));
		t.AddPlayerToTable(new Player("Joe"));
		t.AddPlayerToTable(new Player("Jim"));
		t.AddPlayerToTable(new Player("Jane"));

		
		Rule rle = new Rule(eGame.TexasHoldEm);
		GamePlay gp = new GamePlay(t, rle);
		try {
			gp.StartGame();
		} catch (DeckException | HandException e) {
			fail("Couldnt' execute gp.StartGame");
		}
	}

}
