package fr.bstevant.sideman;

import java.io.File;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ZoomControls;

public class Sideman extends ListActivity {
	private File[] files;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sideman_filechooser);
        
        
        File sidemandir = new File("/sdcard/Sideman");
        if (sidemandir.exists()) {
        	files = sidemandir.listFiles();
        	if (files != null) {
        		Log.v("Sideman", files.toString());
        		setListAdapter(new ArrayAdapter<File>(this, R.layout.list_item, files));
        	} else {
        		Log.v("Sideman", "Empty file list");        		
        	}
        } else {
    		Log.v("Sideman", "Directory Sideman does not exist");
        }
    }
    
    public void onListItemClick(ListView lv, View v, int position, long id) {
    	Intent intentPlayer = new Intent(this, SidemanPlayer.class);
    	String name = files[position].getName();
    	String sdcardName = "/Sideman/" + name;
    	intentPlayer.putExtra("filename", sdcardName);
    	startActivity(intentPlayer);
    }
}