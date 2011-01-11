package fr.bstevant.sideman;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.GridView;
import android.widget.ZoomControls;

public class SongView extends GridView {
	Context mContext;
	Song mSong;
	int height, width;
	int nbBarPerLine;
	ZoomControls zoom;
	final SongView myself;
	
	public SongView(Context context, ZoomControls z) {
		super(context);
		mContext = context;
		nbBarPerLine = 2;
		zoom = z;
		myself = this;
	}
	
	public void setSong(Song s) {
		mSong = s;
		zoom.setOnZoomInClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				Log.v("Sideman","Zoom -");
				if (nbBarPerLine > 1) nbBarPerLine--;
				myself.changeBarZoom();
			}
		});
		zoom.setOnZoomOutClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				Log.v("Sideman","Zoom +");
				if (nbBarPerLine < 8) nbBarPerLine++;
				myself.changeBarZoom();
			}
		});
	}

	public void changeBarZoom() {
		super.invalidate();
		if (mSong == null) return;
		setAdapter(new BarsAdapter(mContext, mSong, width, nbBarPerLine));

		if (nbBarPerLine == 1) 
			zoom.setIsZoomInEnabled(false);
		else
			zoom.setIsZoomInEnabled(true);
			
		if (nbBarPerLine == 8) 
			zoom.setIsZoomOutEnabled(false);
		else
			zoom.setIsZoomOutEnabled(true);
	}
	
	@Override
	public void onGlobalLayout() {
		super.onGlobalLayout();
		ViewTreeObserver vto = getViewTreeObserver();
        vto.removeGlobalOnLayoutListener(this);

		if (mSong == null) return;
		height = getHeight();
		width = getWidth();
		//Log.v("Sideman","SongView w: " + width + " h: " + height);
		setAdapter(new BarsAdapter(mContext, mSong, width, nbBarPerLine));
	}
}
