package fr.bstevant.sideman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream; 
import java.io.FileNotFoundException;
import java.io.IOException; 
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.os.Environment;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class SongReader {

	ArrayList<String> fileContent;
	String xmlString;
	Song theSong;
	
	public SongReader(String file) {
		fileContent = new ArrayList<String>();
		xmlString = "";
		theSong = new Song("", "120", "4/4");
		readFile(file);
		parseFile(file);
	}
		
	public Boolean isEmpty() {
		return theSong.isEmpty();
	}
	
	public int count() {
		return theSong.countBars();
	}
	
	public Bar get(int i) {
		return theSong.get(i);
	}
	
	private void readFile(String file) {
		try{		 
			   File f = new File(Environment.getExternalStorageDirectory()+file); 
			   FileInputStream fileIS = new FileInputStream(f);
			   BufferedReader buf = new BufferedReader(new InputStreamReader(fileIS));	 
			   String readString = new String();
			 
			   while((readString = buf.readLine())!= null){	 
			      fileContent.add(readString);
			      xmlString += readString;
			   }
			
			} catch (FileNotFoundException e) { 
			   e.printStackTrace();
			} catch (IOException e){
			   e.printStackTrace();
			}
	}
	
	private void parseFile(String file) {

		// On passe par une classe factory pour obtenir une instance de sax
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = null;
		
		try {
			// On "fabrique" une instance de SAXParser
			parser = factory.newSAXParser();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}

		/*
		 * Le handler sera gestionnaire du fichier XML c'est à dire que c'est lui qui sera chargé
		 * des opérations de parsing. On vera cette classe en détails ci après.
		 */
		DefaultHandler handler = new SongXMLHandler();
		try {
			// On parse le fichier XML
			parser.parse("file:///mnt/sdcard"+file, handler);

			// On récupère directement la liste des feeds
			theSong = ((SongXMLHandler) handler).getSong();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
