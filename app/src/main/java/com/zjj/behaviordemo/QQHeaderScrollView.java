package com.zjj.behaviordemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Scroller;

/**
 * Created by zjj on 2018/7/31.
 */
public class QQHeaderScrollView extends ListView {
    private static final String TAG = "zjj";

    private ImageView imageView;

    private int mImageViewHeight;//高度

    private int maxImageViewHeight;//最大高度

    private Scroller scroller;

    public void setZoomImageView(ImageView imageView){ this.imageView = imageView; }



    public QQHeaderScrollView(Context context) {
        super(context);
    }

    public QQHeaderScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mImageViewHeight = context.getResources().getDimensionPixelSize(R.dimen.size_height);
        this.maxImageViewHeight = context.getResources().getDisplayMetrics().heightPixels*2/3;
        scroller = new Scroller(context);

    }
    public QQHeaderScrollView(Context context,AttributeSet attrs,int deStyleAttr){
        super(context,attrs,deStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == MotionEvent.ACTION_DOWN){
            if (imageView.getAnimation() != null){
                imageView.clearAnimation();
            }
        }
        if(action == MotionEvent.ACTION_UP){
            //使用动画
            ResetAnimation resetAnimation = new ResetAnimation(mImageViewHeight);
            resetAnimation.setInterpolator(new OvershootInterpolator());
            resetAnimation.setDuration(700);
            imageView.startAnimation(resetAnimation);
            //使用scroller
//            if(imageView.getLayoutParams().height > mImageViewHeight) {
//                smoothScroll(mImageViewHeight);
//            }
        }
        return super.onTouchEvent(ev);
    }
    private void smoothScroll(int targetHeight){
        scroller.startScroll(0,imageView.getHeight(),0,-(imageView.getHeight() - targetHeight),700);
    }
    public class  ResetAnimation extends Animation{
        private int extraHeight;
        private int currentHeight;

        public ResetAnimation(int targetHeight){
            currentHeight = imageView.getHeight();
            extraHeight = imageView.getHeight()  - targetHeight;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            imageView.getLayoutParams().height = (int)(currentHeight - extraHeight*interpolatedTime);
            imageView.requestLayout();
            super.applyTransformation(interpolatedTime, t);
        }
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        Log.i(TAG, "overScrollBy: "+deltaY);
        imageView.getLayoutParams().height = imageView.getHeight() - deltaY;
        if(imageView.getLayoutParams().height > maxImageViewHeight){
            imageView.getLayoutParams().height = maxImageViewHeight;
        }
        imageView.requestLayout();
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        View header = (View)imageView.getParent();

        int deltedY = header.getTop();
        Log.i(TAG, "onSizeChanged: "+deltedY);
        if(imageView.getHeight() > mImageViewHeight){
            imageView.getLayoutParams().height = imageView.getHeight() + deltedY;
            header.layout(header.getLeft(),0,header.getRight(),header.getHeight());
            imageView.requestLayout();
        }
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public void computeScroll() {
        if(scroller.computeScrollOffset()){
            imageView.getLayoutParams().height = scroller.getCurrY();
            imageView.requestLayout();
        }
        super.computeScroll();
    }
}
