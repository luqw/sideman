package fr.bstevant.sideman;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TableLayout;
import android.widget.TextView;

public class BarsAdapter extends BaseAdapter {
	private Context mContext;
	private SongReader mSong;
	private int nbBarPerLine = 2;
	private int nbLines;
	
	public BarsAdapter(Context c, SongReader song) {
		mContext = c;
		mSong = song;
		nbLines = mSong.count() / nbBarPerLine;
		if ((mSong.count() % nbBarPerLine) != 0) {
			nbLines++;
		}
		Log.v("Sideman","Song has "+mSong.count()+" bars");
		Log.v("Sideman","Display "+nbBarPerLine+" bar/line = "+nbLines+" lines");
	}
	
	public int getCount() {
		//return mSong.count() % nbBarPerLine;
		return nbLines;
	}

	public Object getItem(int position) {
		return mSong.get(position);
	}
	
	public long getItemId (int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		//TextView textView;
		BarView bv;
		if (convertView == null) {  // if it's not recycled, initialize some attributes
            //textView = new TextView(mContext);
            //imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //imageView.setPadding(8, 8, 8, 8);
			bv = new BarView(mContext);
        } else {
        	bv = (BarView)convertView;
        	bv.clearBar();
        }
        for (int i=0;i<nbBarPerLine;i++) {
        	Bar b = mSong.get((position*nbBarPerLine)+i);
        	if (b != null) {
        		bv.addBar(b, i);
        	}
        }
		//textView.setText(mSong.get(position).toString());
		return bv;
	}

}
