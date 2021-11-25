package com.hsns.picture.main.view;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.hsns.base.listener.SlideHeaderCallback;
import com.hsns.base.utils.BaseUtils;
import com.hsns.base.utils.SharePreUtils;
import com.hsns.picture.PictureApplication;
import com.hsns.picture.R;
import com.wildma.pictureselector.PictureBean;
import com.wildma.pictureselector.PictureSelector;

import de.hdodenhof.circleimageview.CircleImageView;

public class SlideHeaderLayout implements View.OnClickListener {
    private TextView slideWelTex;
    private TextView slideTextLabel;
    private CircleImageView cusImg;//自定义头像
    private static final String TAG = "SlideHeaderLayout";
    private SlideHeaderCallback slideHeaderCallback;
    public static final int TYPE_TRANSLOGIN = 0;
    public static final int TYPE_LOADIMG = 1;


    public SlideHeaderLayout(SlideHeaderCallback slideHeaderCallback, NavigationView navigationView) {
        this.slideHeaderCallback = slideHeaderCallback;
        initNaviView(navigationView);
        setHeaderText();
        initSavedImgData();
    }

    /**
     * 加载存储的头像数据
     */
    public void initSavedImgData() {
        if (BaseUtils.isLoginSuccess) {
            String path = SharePreUtils.getPicPath(PictureApplication.getApplication());
            boolean isCut = SharePreUtils.isCut(PictureApplication.getApplication());
            loadHeadPortrait(isCut, path);
        } else {
            cusImg.setImageResource(R.mipmap.touxiang);
        }
    }

    /**
     * 初始化侧滑栏头部view
     *
     * @param navigationView 头部根view
     */
    private void initNaviView(NavigationView navigationView) {
        View view = navigationView.getHeaderView(0);
        slideWelTex = (TextView) view.findViewById(R.id.text_welcome);
        slideTextLabel = (TextView) view.findViewById(R.id.text_label);
        cusImg = (CircleImageView) view.findViewById(R.id.photo_img);
        view.setOnClickListener(this);
        cusImg.setOnClickListener(this);
        slideTextLabel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!BaseUtils.isLoginSuccess) {
            slideHeaderCallback.onSlideCallback(TYPE_TRANSLOGIN);
            return;
        }
        switch (v.getId()) {
            case R.id.slide_layout:
                break;
            case R.id.photo_img:
                slideHeaderCallback.onSlideCallback(TYPE_LOADIMG);
                break;
            case R.id.text_label:

                break;
        }
    }


    /**
     * 设置头部的text值
     */
    public void setHeaderText() {
        String loginText = PictureApplication.getApplication().getString(R.string.welcome) + SharePreUtils.getCurLoginInfo(PictureApplication.getApplication());
//        String labelText=SharePreUtils.getCurLoginInfo(PictureApplication.getApplication());
        String notLoginText = PictureApplication.getApplication().getString(R.string.no_login);
        slideWelTex.setText(BaseUtils.isLoginSuccess ? loginText : notLoginText);
//        slideTextLabel.setText(BaseUtils.isLoginSuccess?labelText:"");
    }


    /**
     * 加载头像
     *
     * @param isCut 是否被裁减
     */
    public void loadHeadPortrait(boolean isCut, String imgPath) {
        Log.d(TAG, "isCut==>" + isCut + " imgPath==>" + imgPath);
        if (TextUtils.isEmpty(imgPath)) return;
        if (isCut) {
            cusImg.setImageBitmap(BitmapFactory.decodeFile(imgPath));
        } else {
            cusImg.setImageURI(Uri.parse(imgPath));
        }
    }

    /**
     * 图片选择结果回调
     *
     * @param requestCode 请求的code
     * @param resultCode  返回的code
     * @param data        数据对象
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                PictureBean pictureBean = data.getParcelableExtra(PictureSelector.PICTURE_RESULT);
                if (pictureBean != null) {
                    boolean isCut = pictureBean.isCut();
                    String imgPath = isCut ? pictureBean.getPath() : pictureBean.getUri().toString();
                    loadHeadPortrait(isCut, imgPath);
                    SharePreUtils.storePicInfo(PictureApplication.getApplication(), imgPath, isCut);
                }
            }
        }
    }
}
