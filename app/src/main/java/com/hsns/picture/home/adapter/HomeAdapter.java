package com.hsns.picture.home.adapter;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.hsns.base.bean.HomeInfo;
import com.hsns.picture.R;

import java.io.File;
import java.util.List;


public class HomeAdapter extends BaseAdapter<HomeInfo.Datas, HomeAdapter.HomeHolder> {

    public HomeAdapter(List<HomeInfo.Datas> infos, Context mContext) {
        super(infos, mContext);
    }


    @Override
    public HomeAdapter.HomeHolder getHolder(View view) {
        return new HomeHolder(view);
    }


    @Override
    public int getLayoutId() {
        return R.layout.home_ry_item;
    }

    @Override
    public void onBindView(HomeHolder holder, int position) {
        if (holder != null & infos != null && infos.size() != 0) {
            String shareUser = infos.get(position).getShareUser();
            String author = infos.get(position).getAuthor();
            String time = infos.get(position).getNiceShareDate();
            String tittle = infos.get(position).getTitle();
            String style = infos.get(position).getSuperChapterName() + "/" + infos.get(position).getChapterName();
            holder.author.setText(!TextUtils.isEmpty(shareUser) ? shareUser : author);
            holder.time.setText(time);
            holder.tittle.setText(tittle);
            holder.style.setText(style);
            holder.setPosition(position);
        }
    }

    class HomeHolder extends BaseAdapter.BaseHolder {
        private TextView author;
        private TextView time;
        private TextView tittle;
        private TextView style;

        public HomeHolder(@NonNull View itemView) {
            super(itemView);
            author = (TextView) itemView.findViewById(R.id.home_ry_item_author);
            time = (TextView) itemView.findViewById(R.id.home_ry_item_time);
            tittle = (TextView) itemView.findViewById(R.id.home_ry_item_tittle);
            style = (TextView) itemView.findViewById(R.id.home_ry_item_style);
        }

    }


}
