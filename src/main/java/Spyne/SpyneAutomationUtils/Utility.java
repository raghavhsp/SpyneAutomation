package Spyne.SpyneAutomationUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;


public class Utility {
	 private static Logger log = LoggingUtils.getLogger(Utility.class);
	
	/**
	 * @author raghav
	 * @since 08092024
	 * @param timeInSeconds
	 */
	
	public static void sleep(int timeInSeconds) {
		try {
			Thread.sleep(timeInSeconds * 1000);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	/**
	 * @author raghav
	 * @since 08092024
	 * @return Properties
	 */
	
	public static String getProperty (String key) {
		Properties properties = new Properties();
		InputStream inputSteam = null;
		
		// Get base and Resource path
		String baseDirectory= System.getProperty("user.dir");
		String resourceDirectory=File.separator+"src" +File.separator+"main"+File.separator+"resources"+File.separator+"config.properties";
		
		try {
			inputSteam= new FileInputStream(new File (baseDirectory+resourceDirectory));
			properties.load(inputSteam);
			if (inputSteam!=null) {
				inputSteam.close();
			}
		}
		catch(Exception e)
		{
			log.error("Failed to find config file at path :"+baseDirectory+resourceDirectory);
			
		}
		
		return properties.getProperty(key);

	}

	
	/**
	 * @author raghav
	 * @since 08092024
	 * @return Properties
	 */
	
	public static boolean deleteFileOfDirectory(String filepath)
	{
		// Get all files
		File directory = null;
		directory = new File(filepath);
		File[] files;
		files = directory.listFiles();
		
		// Delete all files
		for(File file :files)
		{
			file.delete();
		}
		
		return (directory.listFiles().length==0 ? true : false);
		
	}
	
	
	/**
	 * @author raghav
	 * @since 08092024
	 * @return Properties
	 */
	
	public static File[] getFiles(String filepath)
	{
		// Get all files
		File directory = null;
		directory = new File(filepath);
		File[] files = directory.listFiles();

		return (files);
		
	}
}
