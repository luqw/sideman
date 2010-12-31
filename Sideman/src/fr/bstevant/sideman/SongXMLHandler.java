package fr.bstevant.sideman;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class SongXMLHandler extends DefaultHandler{

	private final String SONG = "song";
	private final String BAR = "bar";
	private final String CHORD = "chord";

	// the song
	private Song theSong;
	
	// currentBar
	private Bar currentBar;

	@Override
	public void processingInstruction(String target, String data) throws SAXException {
		super.processingInstruction(target, data);
	}

	public SongXMLHandler() {
		super();
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
	}

	@Override
	public void startElement(String uri, String localName, String name,	Attributes attributes) throws SAXException {
		if (localName.equalsIgnoreCase(SONG)){
			String meter = attributes.getValue("meter");
			String tempo = attributes.getValue("tempo");
			String songname = attributes.getValue("name");
			theSong = new Song(songname, tempo, meter);
			//Log.v("Sideman", "Song tag parsed");
		}

		if (localName.equalsIgnoreCase(BAR)){
			currentBar = new Bar(theSong.nbpm);
			//Log.v("Sideman", "Bar tag parsed");
		}
		
		if (localName.equalsIgnoreCase(CHORD)){
			int beat = Integer.parseInt(attributes.getValue("beat"));
			String chord = attributes.getValue("chord");
			currentBar.setBeat(beat, chord);
			//Log.v("Sideman", "Chord tag parsed");
		}
	}


	@Override
	public void endElement(String uri, String localName, String name) throws SAXException {		

		if (localName.equalsIgnoreCase(CHORD)){
		}
		if (localName.equalsIgnoreCase(BAR)){
			theSong.addBar(currentBar);
		}
	}

	public Song getSong() {
		return theSong;
	}
}
