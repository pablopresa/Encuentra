package helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesHelper {
	
	String tipo;
	Properties prop;

	public PropertiesHelper(String tipo) {
		this.tipo = tipo;
		prop=new Properties();
	}
	public void loadProperties() throws Exception{
		ClassLoader classLoader = null;
		try 
		{
			classLoader = Thread.currentThread().getContextClassLoader();
			//InputStream stream = classLoader.getResourceAsStream("/properties/"+tipo+".properties");
			InputStream stream = this.getClass().getResourceAsStream("/properties/"+tipo+".properties");
			
			//System.out.println(Thread.currentThread().getContextClassLoader());
			prop.load(stream);
		} 
		catch (Exception e) 
		{
			String classpath = System.getProperty("java.class.path");
			String [] pat = classpath.split(";");
			String finclass = pat[0];
			//System.out.println(finclass);
		     prop.load(new FileInputStream(finclass +"\\properties\\"+tipo+".properties"));
			//e.printStackTrace();
			//InputStream stream = classLoader.getResourceAsStream(file.getAbsolutePath());
			
		}
	}
	
	public String getValue(String key)
	{
		return prop.getProperty(key);
	}
	public void setValue(String key, String value)
	{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		OutputStream out;
		try 
		{
			out = new FileOutputStream("test.txt");
		
			prop.setProperty(key, value);
			prop.store(out, "");
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}