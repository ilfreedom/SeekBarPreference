package com.example.seekbarpreferencesample;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity{

	private MyPreferenceFragment mPrefer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			if (mPrefer == null) {
				mPrefer = new MyPreferenceFragment();
			}
			if (mPrefer.isAdded()) {
				ft.show(mPrefer);
			} else {
				ft.add(R.id.fragment_content, mPrefer, "MyPreferenceFragment");
			}
			findViewById(R.id.textview).setVisibility(View.GONE);
			ft.addToBackStack(null);
			ft.commit();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
