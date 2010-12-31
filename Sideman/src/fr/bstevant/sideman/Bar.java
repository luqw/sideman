package fr.bstevant.sideman;

import java.util.ArrayList;

public class Bar {
	String[] beats;
	int iNbpm;
	
	public Bar(int nbpm) {
		beats = new String[nbpm];
		iNbpm = nbpm;
		int i;
		for (i=0; i < iNbpm; i++) {
			beats[i]="";
		}
	}
	
	public void setBeat(int i, String chord) {
		if (i < iNbpm)
			beats[i] = chord;
	}

	public String toString() {
		String s = "| ";
		int i;
		for (i=0; i < iNbpm; i++) {
			s+=String.format("%8s", beats[i]);
		}
		return s;
	}
}
