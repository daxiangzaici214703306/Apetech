package com.hsns.base.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.hsns.base.utils.BaseUtils;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    public FragmentManager mFragmentManager;
    private Fragment mCurrentFragment;
    private static final String TAG = "BaseActivity";
    public String tag = BaseUtils.TAG_HOME;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mFragmentManager = getSupportFragmentManager();
        Intent intent = getIntent();
        if (intent != null) {
            tag = getIntent().getStringExtra(BaseUtils.TAG_FRAGMENT);
            if (TextUtils.isEmpty(tag)) {
                tag = BaseUtils.TAG_HOME;
            }
        }
        initView();

    }

    /**
     * 获取对应layout的id
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化view
     *
     * @return
     */
    public abstract void initView();

    /**
     * 切换Fragment
     */
    public void showFragment(int id, Fragment fragment, String tag) {
        Log.i(TAG, "id==>" + id + " fragment==>" + fragment + " tag==>" + tag);
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (mCurrentFragment != null) {
            transaction.hide(mCurrentFragment);
        }
        Fragment tempFragment = mFragmentManager.findFragmentByTag(tag);
        Log.i(TAG, "is exit fragment targetFragment==>" + tempFragment);
        if (tempFragment != fragment) {
            transaction.add(id, fragment, tag);
        } else {
            transaction.show(fragment);
        }
        transaction.commit();
        mCurrentFragment = fragment;
    }


}
