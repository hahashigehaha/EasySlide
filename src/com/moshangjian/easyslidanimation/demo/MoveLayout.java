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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moshangjian.easyslidanimation.EasySlideInter;
import com.moshangjian.easyslidanimation.R;

public class MoveLayout extends RelativeLayout implements EasySlideInter {

	private ImageView text;
	private ObjectAnimator textAnimator;
	private ObjectAnimator textAnimator2;

	public MoveLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	public MoveLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	public MoveLayout(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context){
		LayoutInflater.from(context).inflate(R.layout.move_view, this) ;
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		text = (ImageView) findViewById(R.id.move_image);
		initAnimation(); 
	}
	
	
	@Override
	public void contentSlide(int progress) {
		textAnimator.setCurrentPlayTime(progress);
		textAnimator2.setCurrentPlayTime(progress);
	}

	@Override
	public void resetContent() {
		textAnimator.setCurrentPlayTime(100);
		textAnimator2.setCurrentPlayTime(100);
	}
	
	
	private void initAnimation(){
		textAnimator = ObjectAnimator.ofFloat(text, "scaleX", 0 , text.getScaleX() ); 
		textAnimator.setDuration(100)  ;
		textAnimator.setInterpolator(new LinearInterpolator()) ;
		
		textAnimator2 = ObjectAnimator.ofFloat(text, "scaleY", 0 , text.getScaleY()); 
		textAnimator2.setDuration(100)  ;
		textAnimator2.setInterpolator(new LinearInterpolator()) ;
		
	}
}
