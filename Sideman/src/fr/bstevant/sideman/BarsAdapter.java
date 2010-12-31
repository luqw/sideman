package fr.bstevant.sideman;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BarsAdapter extends BaseAdapter {
	private Context mContext;
	private SongReader mSong;
	
	public BarsAdapter(Context c, SongReader song) {
		mContext = c;
		mSong = song;
	}
	
	public int getCount() {
		return ((mSong.count() / 2));
	}

	public Object getItem(int position) {
		return mSong.get(position);
	}
	
	public long getItemId (int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		TextView textView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            textView = new TextView(mContext);
            //imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //imageView.setPadding(8, 8, 8, 8);
        } else {
            textView = (TextView) convertView;
        }
        String s1 = mSong.get(2*position).toString();
        String s2 = mSong.get(2*position+1).toString();
        textView.setText(s1 + s2);
		return textView;
	}

}
