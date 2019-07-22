package pkgBetStrategy;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class LoadStrategy {

	public static void main(String[] args) {
		
		ReadXMLFile();
		

	}
	
	private static BetRound ReadXMLFile() {

		BetRound ebr = null;

		String basePath = new File("").getAbsolutePath();
		basePath = basePath + "\\src\\main\\resources\\PlayerStrategy\\BetStrategy.xml";
		File file = new File(basePath);

		System.out.println(file.getAbsolutePath());
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(BetRound.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			ebr = (BetRound) jaxbUnmarshaller.unmarshal(file);
			System.out.println(ebr);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return ebr;

	}

}
