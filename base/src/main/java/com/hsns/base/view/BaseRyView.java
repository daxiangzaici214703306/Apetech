package com.hsns.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class BaseRyView extends RecyclerView {
    private float interceptStartY; //拦截触摸开始y的位置
    private static final int MOVE_Y = 50;//y轴移动的值,判断是否显示侧滑栏
    private static final String TAG = "BaseRyView";

    public BaseRyView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        Log.i(TAG, "onTouchEvent action==>" + event.getAction());
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_MOVE:
//            case MotionEvent.ACTION_UP:
//                if (Math.abs(event.getY() - interceptStartY) < MOVE_Y) {
//                    return false;
//                }
//                break;
//            case MotionEvent.ACTION_DOWN:
//                interceptStartY = event.getY();
//                break;
//        }
//        return super.onTouchEvent(event);
//    }
}
