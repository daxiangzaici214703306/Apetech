package com.hsns.picture.main.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hsns.base.utils.BaseUtils;
import com.hsns.base.utils.SharePreUtils;
import com.hsns.picture.R;

public class PicSlideLayout extends FrameLayout {
    private View view; //侧滑栏layout对应的view
    private int itemId;//侧滑栏layout的id
    private float itemWidth;//侧滑栏layout的宽度
    float startX; //触摸开始x的位置
    float startY; //触摸开始y的位置
    //    float interceptStartX; //拦截触摸开始x的位置
//    float interceptStartY; //拦截触摸开始y的位置
    private static final String TAG = "PicSlideLayout";
    private static final int MOVE_X = 50;//x轴移动的值,判断是否显示侧滑栏
    private static final int MOVE_Y = 50;//y轴移动的值,判断是否显示侧滑栏
    private ObjectAnimator animator;//侧滑栏滑动动画
    private static final int TIME_ANIM = 1000;//动画执行的时间
    private boolean intercept = false;

    public PicSlideLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.picValue);
        itemId = typedArray.getResourceId(R.styleable.picValue_itemId, 0);
        itemWidth = typedArray.getDimension(R.styleable.picValue_itemWidth, 0);
        typedArray.recycle();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent event.getAction==>" + event.getAction()+" isLoginSuccess==>"+BaseUtils.isLoginSuccess);
        if (BaseUtils.isLoginSuccess) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_DOWN:
                    if (startX == 0 && startY == 0) {
                        startX = event.getX();
                        startY = event.getY();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    doUpOperation(event.getX(), event.getY());
                    startX = 0;
                    startY = 0;
                    break;
            }
        }
        return super.onTouchEvent(event);
    }


    /**
     * 手指拿起来时候的操作
     *
     * @param x 手指停止x的位置
     * @param y 手指停止时候y的位置
     * @return 是否执行滑动事件
     */
    private void doUpOperation(float x, float y) {
        Log.i(TAG, "doUpOperation: x==>" + x + " y==>" + y + " startX==>" + startX + " startY==>" + startY);
//        if (Math.abs(y - startY) < MOVE_Y) {
        if (x - startX > MOVE_X) {
            Log.i(TAG, "doUpOperation: visible silde view");
            doTranslationOperation(-itemWidth, 0);
        } else {
            Log.i(TAG, "doUpOperation: invisible silde view");
            doTranslationOperation(0, -itemWidth);
        }
//        }
    }


    /**
     * 执行动画操作
     */
    private void doTranslationOperation(float startX, float endX) {
        if (itemId != 0 && view == null) {
            view = findViewById(itemId);
        }
        Log.i(TAG, "doTranslationOperation: view==>" + view + " itemWidth==>" + itemWidth + " view.getX==>" + view.getX() + " startX==>" + startX + " endX==>" + endX);
        if (view != null && view.getX() != endX && view.getX() == startX) {
            animator = ObjectAnimator.ofFloat(view, "translationX", startX, endX);
            animator.setDuration(TIME_ANIM);
            animator.start();
        }
    }

    /**
     * 隐藏当前view
     */
    public void hide() {
        doTranslationOperation(0, -itemWidth);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Log.i(TAG, "onInterceptTouchEvent event.getAction==>" + event.getAction());
        return super.onInterceptTouchEvent(event);//
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i(TAG, "dispatchTouchEvent event.getAction==>" + event.getAction());
        return super.dispatchTouchEvent(event);
    }
}



