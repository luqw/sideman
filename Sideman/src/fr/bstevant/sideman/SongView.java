package fr.bstevant.sideman;

import android.content.Context;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.GridView;

public class SongView extends GridView {
	Context mContext;
	Song mSong;
	int height, width;
	
	public SongView(Context context) {
		super(context);
		mContext = context;
	}
	
	public void setSong(Song s) {
		mSong = s;
	}

	@Override
	public void onGlobalLayout() {
		super.onGlobalLayout();
		ViewTreeObserver vto = getViewTreeObserver();
        vto.removeGlobalOnLayoutListener(this);

		if (mSong == null) return;
		height = getHeight();
		width = getWidth();
		Log.v("Sideman","SongView w: " + width + " h: " + height);
		setAdapter(new BarsAdapter(mContext, mSong, width));
	}
}
