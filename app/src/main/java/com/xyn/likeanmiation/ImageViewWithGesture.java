package com.xyn.likeanmiation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by lcx on 2015/6/9.
 */
public class ImageViewWithGesture extends ImageView {

    private static final String TAG = ImageViewWithGesture.class.getSimpleName();

    public void setGestureDetector(Context context, GestureDetector.OnGestureListener gestureListener) {
        gestureDetector = new GestureDetector(context, gestureListener);
    }

//    private float currentTouchX, currentTouchY;
//
//    public float getCurrentTouchX() {
//        return currentTouchX;
//    }
//
//    public float getCurrentTouchY() {
//        return currentTouchY;
//    }
//
//    public void setCurrentTouchXY(float currentTouchX,float currentTouchY) {
//        this.currentTouchX = currentTouchX;
//        this.currentTouchY = currentTouchY;
//    }

    GestureDetector gestureDetector;

    public ImageViewWithGesture(Context context) {
        super(context);
    }

    public ImageViewWithGesture(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    //skiping measure calculation and drawing

    // delegate the event to the gesture detector
    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Log.i(TAG, "x=" + getCurrentTouchX() + ",y=" + getCurrentTouchY());
        if (gestureDetector == null) {
//            Log.w(TAG, "gestureDetector == null");
            return super.onTouchEvent(event);
        }

//        setCurrentTouchXY(event.getX(),event.getY());
        return gestureDetector.onTouchEvent(event);
    }


}
