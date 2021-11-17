package com.hsns.picture.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hsns.base.bean.NaviInfo;
import com.hsns.base.manager.PageChangeManger;
import com.hsns.base.utils.BaseUtils;
import com.hsns.base.utils.UiUtils;
import com.hsns.picture.main.view.MainActivity;
import com.hsns.picture.PictureApplication;
import com.hsns.picture.R;
import java.util.List;


public class NaviAdapter extends BaseAdapter<NaviInfo.Data, NaviAdapter.NaviHolder> {
    private static final String TAG = "NaviAdapter";

    public NaviAdapter(List<NaviInfo.Data> infos, Context mContext) {
        super(infos, mContext);
    }


    @Override
    public NaviAdapter.NaviHolder getHolder(View view) {
        return new NaviHolder(view);
    }


    @Override
    public int getLayoutId() {
        return R.layout.home_ry_item;
    }

    @Override
    public void onBindView(NaviHolder holder, int position) {
        if (holder != null & infos != null && infos.size() != 0) {
            String tittle = infos.get(position).getName();
            List<NaviInfo.Root> articles = infos.get(position).getArticles();
            SpannableStringBuilder childs = new SpannableStringBuilder();
            for (NaviInfo.Root root : articles) {
                int val = childs.length();
                childs.append(root.getTitle() + "/");
                String link = root.getLink();
                childs.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        Log.d(TAG, "onClick link==>" + link);
                        UiUtils.transFragment(PictureApplication.getApplication(), BaseUtils.TAG_WEBVIEW, MainActivity.class);
                        PageChangeManger.getInstance().getListener().onPageChange(link);
                    }

                    @Override
                    public void updateDrawState(@NonNull TextPaint ds) {
                        super.updateDrawState(ds);
                    }
                }, val, childs.length() - 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                Log.d(TAG, "val==>" + val);
            }
            holder.tittle.setText(tittle);
            holder.content.setText(childs);
            holder.content.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }


    class NaviHolder extends BaseAdapter.BaseHolder {
        private TextView author;
        private TextView time;
        private TextView content;
        private TextView tittle;


        public NaviHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setBackgroundColor(Color.WHITE);
            author = (TextView) itemView.findViewById(R.id.home_ry_item_author);
            time = (TextView) itemView.findViewById(R.id.home_ry_item_time);
            content = (TextView) itemView.findViewById(R.id.home_ry_item_tittle);
            tittle = (TextView) itemView.findViewById(R.id.home_ry_item_style);

        }

    }


}
