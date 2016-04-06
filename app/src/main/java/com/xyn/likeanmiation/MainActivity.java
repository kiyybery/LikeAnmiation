package com.xyn.likeanmiation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = MainActivity.class.getSimpleName();
    protected View mParent;
    private ImageViewWithGesture mImageView;
    private LayoutInflater inflater;
    private LinearLayout main_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_layout = (LinearLayout) findViewById(R.id.main_layout);
        inflater = LayoutInflater.from(MainActivity.this);
        mParent = inflater.inflate(R.layout.like_view, null);
        main_layout.addView(mParent);


        mImageView = (ImageViewWithGesture) findViewById(R.id.image);
        mImageView.setImageResource(R.drawable.gerenzhuye_bg02);

        GestureListener gestureListener = new GestureListener();
        mImageView.setGestureDetector(this, gestureListener);
    }


    private void setLikeAnimation(float x, float y) {

        final ImageView like_view = new ImageView(MainActivity.this);
        like_view.setImageResource(R.drawable.like_on);
        final RelativeLayout ll = (RelativeLayout) mParent;
        int width = ll.getHeight();
        RelativeLayout.LayoutParams params =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        like_view.setScaleX(1);
        like_view.setScaleY(1);
        like_view.setX(x);
        like_view.setY(y - 120);
        ll.addView(like_view, params);
        like_view.animate()
                .setDuration(1500)
                .alpha(0.5f)
                .scaleX(3)
                .scaleY(3)
                .x(x + 45)
                .y(y - 450)
                .setInterpolator(new DecelerateInterpolator())
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        ll.removeView(like_view);
                    }
                }).start();
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {


        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            return super.onDoubleTapEvent(e);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.i(TAG, "e.YPre" + e.getYPrecision() + ",e.Y=" + e.getY() + "e.RawY=" + e.getRawY());
            setLikeAnimation(e.getRawX(), e.getRawY());
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return super.onSingleTapUp(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return true;
        }
    }
}
