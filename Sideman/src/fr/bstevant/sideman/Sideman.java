package fr.bstevant.sideman;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

public class Sideman extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        SongReader song = new SongReader("/oleo.xml");
        if (!song.isEmpty()) {
        	
        	TextView title = ((TextView)findViewById(R.id.TextViewTitle));
        	title.setText(song.theSong.name);
        	TextView infos = ((TextView)findViewById(R.id.TextViewInfos));
        	infos.setText("Meter: "+song.theSong.meter+" Tempo: "+song.theSong.tempo);
        	
        	GridView gvChords = ((GridView)findViewById(R.id.GridViewBars));
        	gvChords.setAdapter(new BarsAdapter(this, song));
        	
        }
        
    }
}