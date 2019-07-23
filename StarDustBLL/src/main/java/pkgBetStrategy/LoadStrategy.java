package pkgBetStrategy;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import pkgEnum.eBetAction;
import pkgEnum.eBetRound;
import pkgEnum.eBetType;
import pkgEnum.eRank;

public class LoadStrategy {  

	public static void main(String[] args) {

		BetEngine be2 = LoadBettingStrategy();

		BetAmount ba = new BetAmount();
		ba.setiBetMaxPct(99);
		ba.setiBetMinPct(0);
		ba.seteBetType(eBetType.POT);

		BettingStrategy bs = new BettingStrategy();
		bs.setBetAction(eBetAction.CHECK);
		bs.setBetAmount(ba);
		bs.setCard1Rank(eRank.JACK);
		bs.setCard2Rank(eRank.JACK);
		bs.setSameRank(true);
		bs.setSameSuit(false);
		bs.setStrategyNbr(1);

		PlayerPosition pp = new PlayerPosition();
		pp.setBetPositionNbr(1);
		pp.addBettingStrategy(bs);

		BetRound br = new BetRound();
		br.setBetRoundNumber(1);
		br.seteBetRound(eBetRound.PREFLOP);
		br.addPlayerPostiion(pp);
		
		BetEngine be = new BetEngine();
		be.addBetRound(br);

		WriteXMLFile(be);
	}

	private static BetEngine LoadBettingStrategy() {

		BetEngine be = null;

		String basePath = new File("").getAbsolutePath();
		basePath = basePath + "\\src\\main\\resources\\PlayerStrategy\\BetStrategy.xml";
		File file = new File(basePath);

		System.out.println(file.getAbsolutePath());
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(BetEngine.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			be = (BetEngine) jaxbUnmarshaller.unmarshal(file);
			System.out.println(be);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return be;

	}

	private static void WriteXMLFile(BetEngine be) {
		try {

			String basePath = new File("").getAbsolutePath();
			basePath = basePath + "\\src\\main\\resources\\PlayerStrategy\\BetStrategyOutput.xml";
			File file = new File(basePath);

			JAXBContext jaxbContext = JAXBContext.newInstance(BetEngine.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(be, file);
			jaxbMarshaller.marshal(be, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
