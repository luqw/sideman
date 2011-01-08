package fr.bstevant.sideman;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Sideman extends Activity {
	SongView mSongView;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mSongView = new SongView(this);
        
        LinearLayout layoutBars = ((LinearLayout)findViewById(R.id.LayoutBars));
        layoutBars.addView((View)mSongView);
        
        ViewTreeObserver vto = layoutBars.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(mSongView);
        
        SongReader song = new SongReader("/oleo.xml");
        if (!song.isEmpty()) {	
        	TextView title = ((TextView)findViewById(R.id.TextViewTitle));
        	title.setText(song.theSong.name);
        	TextView infos = ((TextView)findViewById(R.id.TextViewInfos));
        	infos.setText("Meter: "+song.theSong.meter+" Tempo: "+song.theSong.tempo);
        	
        	mSongView.setSong(song.theSong);
        }

    }
    
}