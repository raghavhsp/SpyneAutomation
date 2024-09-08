package Spyne.SpyneAutomationUtils;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;


public class LoggingUtils {
	
	public static Logger getLogger(Class clas)
	{
		String filePath=System.getProperty("user.dir")+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"log4j.properties";

		PropertyConfigurator.configure(filePath);
		return Logger.getLogger(clas);
		
	}
}
