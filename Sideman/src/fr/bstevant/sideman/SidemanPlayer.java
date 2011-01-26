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
import android.widget.ZoomControls;

public class SidemanPlayer extends Activity {
	SongView mSongView;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sideman_player);
		ZoomControls zoom = ((ZoomControls)findViewById(R.id.ZoomControls));

        mSongView = new SongView(this, zoom);
        
        LinearLayout layoutBars = ((LinearLayout)findViewById(R.id.LayoutBars));
        layoutBars.addView((View)mSongView);
        
        ViewTreeObserver vto = layoutBars.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(mSongView);
        
        Bundle extras = getIntent().getExtras();
        String filename = "";
        if(extras !=null) {
        	filename = extras.getString("filename");
        }

        SongReader song = new SongReader(filename);
        if (!song.isEmpty()) {	
        	TextView title = ((TextView)findViewById(R.id.TextViewTitle));
        	title.setText(song.theSong.name);
        	TextView infos = ((TextView)findViewById(R.id.TextViewInfos));
        	infos.setText("Meter: "+song.theSong.meter+" Tempo: "+song.theSong.tempo);
        	
        	mSongView.setSong(song.theSong);
        }

    }
    
}