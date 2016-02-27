package com.zj.app.widget;



import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class SeekBarPreference extends DialogPreference implements SeekBar.OnSeekBarChangeListener{
	private static final String TAG="SeekBarPreference";
	private Context mContext;
	private SeekBar mSeekBar;
	private TextView mBarValue;
	private TextView mBarMinValue;
	private TextView mBarMaxValue;
	private int mMaxVal;
	private int mMinVal;
	private int mInitVal;
	private int mValue;
		
	public SeekBarPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.com_zj_widget_SeekBarPreference);
		mMinVal = typedArray.getInt(R.styleable.com_zj_widget_SeekBarPreference_barmin, 20);
		mMaxVal = typedArray.getInt(R.styleable.com_zj_widget_SeekBarPreference_barmax, 70);
		mInitVal = typedArray.getInt(R.styleable.com_zj_widget_SeekBarPreference_barinit, 30);
		setInitValue(mInitVal);
	}
	
	public void setMin(int minVal) {
		this.mMinVal = minVal;
	}

	public void setMax(int maxVal) {
		this.mMaxVal = maxVal;
	}
	public void setInitValue(int initVal){
		if (initVal<mMinVal) mInitVal = mMinVal;
		else if (initVal > mMaxVal) mInitVal = mMaxVal;
		else mInitVal = initVal;			
	}
	public int getValue(){
		return mSeekBar.getProgress();
	}

	@Override
	protected void onBindDialogView(View view) {
		// TODO Auto-generated method stub
		super.onBindDialogView(view);
		Log.d(TAG,"onBindDialogView");
	}

	@Override
	protected void onPrepareDialogBuilder(Builder builder) {
		//
		LayoutInflater factory = LayoutInflater.from(mContext);
		View seekbarView = factory.inflate(R.layout.seekbar_preference, null);
		builder.setView(seekbarView);
		mValue = getPersistedInt(mInitVal); 
		mSeekBar = (SeekBar) seekbarView.findViewById(R.id.SeekBar_paith_width);		
		mSeekBar.setOnSeekBarChangeListener(this);
		mSeekBar.setMax((mMaxVal-mMinVal));
		mSeekBar.setProgress(mValue - mMinVal);
		mBarMinValue = (TextView) seekbarView.findViewById(R.id.SeekBarMin);
		mBarMinValue.setText(""+ mMinVal);
		mBarMaxValue = (TextView) seekbarView.findViewById(R.id.SeekBarMax);
		mBarMaxValue.setText(""+ mMaxVal);
		mBarValue = (TextView) seekbarView.findViewById(R.id.SeekBarVal);
		mBarValue.setText((mSeekBar.getProgress()+mMinVal)+"");
	}

	@Override
	protected void onDialogClosed(boolean positiveResult) {	
		if (positiveResult) {
			persistInt(mValue);
			Log.d(TAG,"onDialogClosed:"+ mSeekBar.getProgress()+"------"+ mValue);
		}		
		super.onDialogClosed(positiveResult);  
	}

	public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {		
		if (fromUser){
			mValue = progress+mMinVal;
			mBarValue.setText(mValue + "");
		}
	}

	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void onRestoreInstanceState(Parcelable state) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(state);
	}

	@Override
	protected Parcelable onSaveInstanceState() {
		// TODO Auto-generated method stub
		return super.onSaveInstanceState();
	}	
	
	
}
