package fr.bstevant.sideman;

import java.util.ArrayList;

import android.util.Log;

public class Song {
	public String name;
	public int tempo;
	public String meter;
	public int nbpm;
	ArrayList<Bar> bars;
	
	public Song(String n, String t, String m) {
		name = n;
		tempo = Integer.parseInt(t);
		meter = m;
		String[] matches = meter.split("/");
		nbpm = Integer.parseInt(matches[0]);
		bars = new ArrayList<Bar> ();
	}
	
	public void addBar(Bar b) {
		bars.add(b);
	}

	public Boolean isEmpty() {
		return bars.isEmpty();
	}
	
	public int countBars() {
		//Log.v("Sideman", "Song has "+bars.size()+" bars");
		return bars.size();
	}
	
	public Bar get(int i) {
		if (i<countBars()) {
			return bars.get(i);
		} else {
			return null;
		}
	}
}

