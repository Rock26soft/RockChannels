package com.rock.tvchannel;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.viewpager.widget.ViewPager;
import android.graphics.Rect;

public class NonSwipeableViewPager extends ViewPager {

    public NonSwipeableViewPager(Context context) {
        super(context);
    }

    public NonSwipeableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Disable touch events
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        // Disable touch events
        return false;
    }

    @Override
    public boolean canScrollHorizontally(int direction) {
        // Disable scrolling by overriding this method
        return false;
    }

    @Override
    protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        // Prevent focus from moving to the next page
        return true;
    }

    @Override
    public View focusSearch(int direction) {
        // Prevent focus from moving to the next page
        return findFocus();
    }
}
