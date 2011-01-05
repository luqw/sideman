package fr.bstevant.sideman;


public class Bar {
	String[] beats;
	public int iNbpm;
	
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
	
	public String getBeat(int i) {
		if (i < iNbpm) {
			return beats[i];
		} else {
			return "";
		}
	}
}
