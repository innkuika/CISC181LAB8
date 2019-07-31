package pkgBetStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

@XmlRootElement
public class BetEngine {

	@XmlElement
	private ArrayList<BetRound> BetRound = new ArrayList<BetRound>();

	public void addBetRound(BetRound br) {
		BetRound.add(br);
	}

	public ArrayList<BetRound> getBetRound() {
		return BetRound;
	}

	public static void main(String[] args) {

		//LoadBettingEngine();
	}

	public static BetEngine LoadBettingEngine() throws SAXException  {

		BetEngine be = null;

		boolean bValidate = BetEngine.validate("BetStrategy.xml", "BetStrategy.xsd");
//		if (!bValidate) {
//			throw new Exception("XSD validation not successful");
//		}
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

	public static boolean validate(String xmlFile, String schemaFile) throws SAXException {

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
			throw se;
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
}
