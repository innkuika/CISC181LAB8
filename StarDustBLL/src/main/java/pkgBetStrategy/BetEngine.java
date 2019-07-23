package pkgBetStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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

		LoadBettingEngine();
	}
	
	public static BetEngine LoadBettingEngine() {

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
}
