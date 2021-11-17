package com.hsns.picture.home.view;

import android.view.View;
import android.widget.Button;

import com.hsns.picture.R;

public class NoDataView implements View.OnClickListener {
    private View view;
    private Button noDataBtn;
    private NoDataClickCallBack mNoDataClickCallBack;

    public NoDataView(View view, NoDataClickCallBack mNoDataClickCallBack) {
        this.view = view;
        this.mNoDataClickCallBack = mNoDataClickCallBack;
        initView();
    }

    /**
     * 初始化view
     */
    private void initView() {
        noDataBtn = (Button) view.findViewById(R.id.no_data_btn);
        noDataBtn.setOnClickListener(this);
    }

    /**
     * 设置view的显示与不显示
     *
     * @param visible
     */
    public void setVisible(boolean visible) {
        if (view != null) {
            view.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.no_data_btn:
                if (mNoDataClickCallBack != null) {
                    mNoDataClickCallBack.onNoDataClickCallBack();
                }
                break;
        }
    }

    /**
     * 点击重试按钮的回调（给到对应的fragment）
     */
    public interface NoDataClickCallBack {
        /**
         * 点击重试按钮的回调方法
         */
        void onNoDataClickCallBack();
    }
}
