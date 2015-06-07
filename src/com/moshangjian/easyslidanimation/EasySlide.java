package com.moshangjian.easyslidanimation;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class EasySlide extends ScrollView {

	public EasySlide(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	public EasySlide(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public EasySlide(Context context) {
		super(context);
	}
	
	
	private LinearLayout contentLayout ; 
	private int childCount ; 
	private int width ; 
	private int height ; 
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		View view = getChildAt(0 );
		if (!(view instanceof LinearLayout)) {
			throw new RuntimeException("This Layout  must is  LinearLayout ") ; 
		}
		contentLayout  = (LinearLayout) view ;
		childCount = contentLayout.getChildCount() ; 
	}

	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		width = getWidth() ; 
		height = getHeight() ; 
	}
	
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		int currentBottom = t + height ;
		int currentTop = t ; 
		Log.e("Slide", "onScrollChange") ;
		
		for (int i = 0; i < childCount; i++) {
			View childView = contentLayout.getChildAt(i )  ;
			if (!(childView  instanceof EasySlideInter)) {
				continue ; 
			}
			int childTop = childView.getTop() ; 
			int childBottom = childView.getBottom() ;
			int childHeight = childView.getHeight() ; 
			EasySlideInter inter = (EasySlideInter) childView ; 
			if ( currentTop > childTop && currentTop < childBottom ) {
				inter.contentSlide(countProgress(currentTop, childBottom, childHeight)); 
			}else if (currentBottom > childTop && currentBottom < childBottom ) {
				inter.contentSlide(100 - countProgress(currentBottom, childBottom, childHeight)); 
			}else if(childTop >= currentTop && childBottom <= currentBottom){
				inter.resetContent();
			}
		}
	}
	
	private int countProgress(int top  ,int childBottom ,  int childHeight){
		return ( childBottom - top ) * 100  / childHeight ;
	}
}
