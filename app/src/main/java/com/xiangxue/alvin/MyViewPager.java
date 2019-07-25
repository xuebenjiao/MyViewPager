package com.xiangxue.alvin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author 享学课堂 Alvin
 * @package com.xiangxue.alvin.viewpagerwrap
 * @fileName MyViewPager
 * @date on 2019/7/3
 * @qq 2464061231
 **/
public class MyViewPager extends ViewPager {
    private static final String TAG = "Zero";

    public MyViewPager(@NonNull Context context) {
        super(context);
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            ViewGroup.LayoutParams lp =  child.getLayoutParams();
           int childWidthSpec = getChildMeasureSpec(widthMeasureSpec,0,lp.width);
           int childHeightSpec = getChildMeasureSpec(heightMeasureSpec,0,lp.height);
           child.measure(childWidthSpec,childHeightSpec);
        }

        int height = 0;
        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                  height = heightSize;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                for (int i = 0; i < getChildCount(); i++) {
                    View child = getChildAt(i);
                    height = Math.max(height,child.getMeasuredHeight());
                }
                break;
            default:
                break;
        }
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }
}
