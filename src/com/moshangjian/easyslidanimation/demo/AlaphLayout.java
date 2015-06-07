package com.moshangjian.easyslidanimation.demo;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moshangjian.easyslidanimation.EasySlideInter;
import com.moshangjian.easyslidanimation.R;

public class AlaphLayout extends LinearLayout implements EasySlideInter {

	private TextView text;
	private ObjectAnimator textAnimator;
	private ObjectAnimator backAnimator;

	public AlaphLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	public AlaphLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	public AlaphLayout(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context){
		LayoutInflater.from(context).inflate(R.layout.alaph_view, this) ;
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		text = (TextView) findViewById(R.id.alaph_text);
		initAnimation(); 
	}
	
	
	@Override
	public void contentSlide(int progress) {
		textAnimator.setCurrentPlayTime(progress);
		backAnimator.setCurrentPlayTime(progress);
	}

	@Override
	public void resetContent() {
		textAnimator.setCurrentPlayTime(100);
		backAnimator.setCurrentPlayTime(100);
	}
	
	
	private void initAnimation(){
		textAnimator = ObjectAnimator.ofInt(text, "textColor", Color.BLUE , Color.RED); 
		textAnimator.setEvaluator(new ArgbEvaluator());
		textAnimator.setDuration(100)  ;
		textAnimator.setInterpolator(new LinearInterpolator()) ;
		
		backAnimator = ObjectAnimator.ofInt(this, "backgroundColor", Color.BLACK , Color.BLUE , Color.BLACK); 
		backAnimator.setEvaluator(new ArgbEvaluator());
		backAnimator.setDuration(100)  ;
		backAnimator.setInterpolator(new LinearInterpolator()) ;
		
	}

}
