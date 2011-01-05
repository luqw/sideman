package fr.bstevant.sideman;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class BarView extends TableLayout {
	private TableRow rowChord;
	private TableRow rowBeat;
	private Context context;
	
	// Dimension
	private int widthBeat;
	private int nbBarPerLine;
	private int sizeText;
	
	public BarView(Context c) {
		super(c);
		this.setMinimumHeight(25);
		context = c;
		
		nbBarPerLine = 2;
		sizeText = 10;
		widthBeat = 39;
		
		// Add rows inside TableLayout
		TableRow trl1,trl2,trl3;
				
		trl1 = new TableRow(c);
		trl1.setBackgroundResource(R.color.light_grey);
		trl1.setMinimumHeight(2);
		this.addView(trl1);
		
		rowChord = new TableRow(c);
		View v = new View(c);
		v.setMinimumHeight(13);
		v.setMinimumWidth(1);
		v.setBackgroundResource(R.color.light_grey);
		rowChord.addView(v);
		this.addView(rowChord);

		trl2 = new TableRow(c);
		trl2.setBackgroundResource(R.color.light_grey);
		trl2.setMinimumHeight(2);
		this.addView(trl2);
		
		rowBeat = new TableRow(c);
		v = new View(c);
		v.setMinimumHeight(3);
		v.setMinimumWidth(1);
		v.setBackgroundResource(R.color.light_grey);
		rowBeat.addView(v);
		this.addView(rowBeat);

		trl3 = new TableRow(c);
		trl3.setBackgroundResource(R.color.light_grey);
		trl3.setMinimumHeight(2);
		this.addView(trl3);
	}
	
	public void addBar(Bar b, int index) {
		int nCurrent = rowChord.getChildCount();
		int nAfter = 1 + ((b.iNbpm+1) * (index+1));
		// If number of elements in the rows is less than the number expected
		// after adding the bar, we need to create elements in the rows
		if (nCurrent < nAfter) {
			int n = nAfter - nCurrent - 1;
			//Log.v("Sideman","Adding "+n+" Textview");
			for (int i=0;i<n;i++) {
				TextView tv = new TextView(context);
				tv.setTextSize(sizeText);
				tv.setMinWidth(widthBeat);
				rowChord.addView(tv);
				
				View v = new View(context);
				v.setMinimumWidth(widthBeat);
				v.setMinimumHeight(3);
				rowBeat.addView(v);
			}
			// Add bar termination line
			View v = new View(context);
			v.setMinimumHeight(13);
			v.setMinimumWidth(1);
			v.setBackgroundResource(R.color.light_grey);
			rowChord.addView(v);
			
			v = new View(context);
			v.setMinimumHeight(3);
			v.setMinimumWidth(1);
			v.setBackgroundResource(R.color.light_grey);
			rowBeat.addView(v);
		}
		
		// Now fill rowChord with text from Bar
		for(int i=0;i<b.iNbpm;i++) {
			int idx = 1+i+((b.iNbpm+1) * index);
			//Log.v("Sideman", "Writing chord to Textview "+idx);
			TextView tv = (TextView) rowChord.getChildAt(1+i+((b.iNbpm+1) * index));
			tv.setText(b.getBeat(i));
		}
		for(int i=0;i<b.iNbpm;i+=2) {
			int idx = 1+i+((b.iNbpm+1) * index);
			//Log.v("Sideman", "Writing chord to Textview "+idx);
			View v = rowBeat.getChildAt(1+i+((b.iNbpm+1) * index));
			v.setBackgroundResource(R.color.light_grey);
		}
	}
	
	public void clearBar() {
		for (int i=0;i<rowChord.getChildCount();i++) {
			View v = rowChord.getChildAt(i);
			if (v instanceof TextView) {
				((TextView) v).setText("");
			}
		}
	}
}
