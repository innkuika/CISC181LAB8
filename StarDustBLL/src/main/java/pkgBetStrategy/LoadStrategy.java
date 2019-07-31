package pkgBetStrategy;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import pkgEnum.eBetAction;
import pkgEnum.eBetOperator;
import pkgEnum.eBetRound;
import pkgEnum.eBetType;
import pkgEnum.eHandStrength;
import pkgEnum.eRank;

public class LoadStrategy {  

	public static void main(String[] args) throws SAXException {

		// SaveBettingEngine();
		LoadBettingStrategy();
		boolean bValidate = BetEngine.validate("BetStrategy.xml", "BetStrategy.xsd");
		if (bValidate)
			System.out.println("BetStrategy.xml is valid");
	 
		

	}
	
	public static void SaveBettingEngine()
	{
		 
		BetEngine be2 = LoadBettingStrategy();

		BetAmount ba = new BetAmount();
		ba.setiBetMaxPct(99);
		ba.setiBetMinPct(0);
		ba.seteBetType(eBetType.POT);

		BettingStrategy bs = new BettingStrategy();
		bs.seteBetCurrentAction(eBetAction.CHECK);
		bs.setBetAmount(ba);
		
		bs.addCardRank(eRank.JACK);
		bs.addCardRank(eRank.JACK);
		bs.setSameRank(true);
		bs.setSameSuit(false);
		bs.setStrategyNbr(1);
		bs.seteHandStrength(null);

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
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(BetEngine.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			be = (BetEngine) jaxbUnmarshaller.unmarshal(file);
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
