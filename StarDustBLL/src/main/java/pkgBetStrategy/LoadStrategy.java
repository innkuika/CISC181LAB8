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
import pkgEnum.eBetRound;
import pkgEnum.eBetType;
import pkgEnum.eRank;

public class LoadStrategy {  

	public static void main(String[] args) {

	 
		LoadBettingStrategy();
		boolean bValidate = validate("BetStrategy.xml", "BetStrategy.xsd");
		System.out.println(bValidate);
	 
		
		/*
		BetEngine be2 = LoadBettingStrategy();

		BetAmount ba = new BetAmount();
		ba.setiBetMaxPct(99);
		ba.setiBetMinPct(0);
		ba.seteBetType(eBetType.POT);

		BettingStrategy bs = new BettingStrategy();
		bs.seteBetCurrentAction(eBetAction.CHECK);
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
	 	*/
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
	
	
	
	private static boolean validate(String xmlFile, String schemaFile) {

		String basePathXML = new File("").getAbsolutePath();
		basePathXML = basePathXML + "\\src\\main\\resources\\PlayerStrategy\\" + xmlFile;		
			
		File fileXML = new File(basePathXML);

		String basePathXSD = new File("").getAbsolutePath();
		basePathXSD = basePathXSD + "\\src\\main\\resources\\PlayerStrategy\\" + schemaFile;
		File fileXSD = new File(basePathXSD);

		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		try {

			Schema schema = schemaFactory.newSchema(fileXSD);
			Validator validator = schema.newValidator();
			validator.setErrorHandler(new MyErrorHandler());
			validator.validate(new StreamSource(fileXML));
			return true;
		} catch (SAXException se) {
	

			System.out.println("   Message: " + se.getMessage());
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}	
	
	
	
	private static class MyErrorHandler extends DefaultHandler {
		public void warning(SAXParseException e) throws SAXException {
			System.out.println("Warning: ");
			printInfo(e);
			throw e;
		}

		public void error(SAXParseException e) throws SAXException {
			System.out.println("Error: ");
			printInfo(e);
			throw e;
		}

		public void fatalError(SAXParseException e) throws SAXException {
			System.out.println("Fattal error: ");
			printInfo(e);
			throw e;
		}

		private void printInfo(SAXParseException e) {
			System.out.println("   Public ID: " + e.getPublicId());
			System.out.println("   System ID: " + e.getSystemId());
			System.out.println("   Line number: " + e.getLineNumber());
			System.out.println("   Column number: " + e.getColumnNumber());
			System.out.println("   Message: " + e.getMessage());
		}
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
