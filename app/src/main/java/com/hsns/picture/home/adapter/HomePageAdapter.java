package com.hsns.picture.home.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hsns.base.bean.HomeInfo;
import com.hsns.picture.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomePageAdapter extends BaseQuickAdapter<HomeInfo.Datas, HomePageAdapter.HomePageHolder> {
    public HomePageAdapter(List<HomeInfo.Datas> infos) {
        super(R.layout.home_ry_item, infos);
    }

    @Override
    protected void convert(@NotNull HomePageHolder holder, HomeInfo.Datas homeInfo) {
        if (holder != null & homeInfo != null) {
            String shareUser = homeInfo.getShareUser();
            String author = homeInfo.getAuthor();
            String time = homeInfo.getNiceShareDate();
            String tittle = homeInfo.getTitle();
            String style = homeInfo.getSuperChapterName() + "/" + homeInfo.getChapterName();
            holder.author.setText(!TextUtils.isEmpty(shareUser) ? shareUser : author);
            holder.time.setText(time);
            holder.tittle.setText(tittle);
            holder.style.setText(style);
        }
    }

    class HomePageHolder extends BaseViewHolder {
        private TextView author;
        private TextView time;
        private TextView tittle;
        private TextView style;

        public HomePageHolder(@NonNull View itemView) {
            super(itemView);
            author = (TextView) itemView.findViewById(R.id.home_ry_item_author);
            time = (TextView) itemView.findViewById(R.id.home_ry_item_time);
            tittle = (TextView) itemView.findViewById(R.id.home_ry_item_tittle);
            style = (TextView) itemView.findViewById(R.id.home_ry_item_style);
        }

    }
}
