package com.moshangjian.easyslidanimation.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.moshangjian.easyslidanimation.EasySlideInter;
import com.moshangjian.easyslidanimation.R;

/**
 *
 */
public class PathLayout extends LinearLayout implements EasySlideInter
{

    private float mRatio = 1f;
    private Paint mPaint;
    private View mPathView;

    public PathLayout(Context context) {
        super(context);
        initPaint();
    }

    public PathLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public PathLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initPaint();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mPathView = findViewById(R.id.pathView);
    }

    private void initPaint() {
    	LayoutInflater.from(getContext()).inflate(R.layout.path_view, this) ; 
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5.0f);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.argb(180, 255, 255, 255));
    }
    
    
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    	super.onSizeChanged(w, h, oldw, oldh);
    	
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        makeAndMeasurePath();
        if(!isInEditMode()) {
            float length = mPathMeasure.getLength();
            PathEffect effect = new DashPathEffect(new float[] {length, length }, length * (1 - mRatio));
            mPaint.setPathEffect(effect);
        }
        canvas.drawPath(mPath, mPaint);
    }

    private PathMeasure mPathMeasure = new PathMeasure();
    private Path mPath = new Path();

    private void makeAndMeasurePath() {
        mPath.reset();
        float translationY = mPathView.getTranslationY();
        mPath.moveTo(mPathView.getLeft(), mPathView.getTop() + translationY);
        mPath.lineTo(mPathView.getLeft() + mPathView.getWidth(), mPathView.getTop() + translationY);
        mPath.lineTo(mPathView.getLeft() + mPathView.getWidth(), mPathView.getTop() + mPathView.getHeight() + translationY);
        mPath.lineTo(mPathView.getLeft(), mPathView.getTop() + mPathView.getHeight() + translationY);
        mPath.close();
        mPathMeasure.setPath(mPath, false);
    }

	@Override
	public void contentSlide(int progress) {
			mRatio = (float)progress / 100f ; 
			mPathView.setAlpha(mRatio);
	        mPathView.setTranslationY(mPathView.getHeight() * (1- mRatio));
	        invalidate();
	}

	@Override
	public void resetContent() {
		mRatio = 1f;
		contentSlide(100);
	}
}
