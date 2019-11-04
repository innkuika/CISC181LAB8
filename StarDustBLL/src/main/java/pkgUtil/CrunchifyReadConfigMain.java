package pkgUtil;

import java.io.IOException;

/**
 * @author Crunchify.com
 * 
 */
 
public class CrunchifyReadConfigMain {
 
	public static void main(String[] args) throws IOException {
		CrunchifyGetPropertyValues properties = new CrunchifyGetPropertyValues();
		
		var props = properties.getPropertyFile();
		
		String company1 = props.getProperty("company1");
		System.out.println(company1);
		
		//properties.getPropValues();
	}
}