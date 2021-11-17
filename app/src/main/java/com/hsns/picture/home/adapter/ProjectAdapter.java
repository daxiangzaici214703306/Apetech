package com.hsns.picture.home.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.hsns.base.bean.ProjectInfo;
import com.hsns.picture.R;

import java.util.List;


public class ProjectAdapter extends BaseAdapter<ProjectInfo.Datas, ProjectAdapter.ProjectHolder> {

    public ProjectAdapter(List<ProjectInfo.Datas> infos, Context mContext) {
        super(infos, mContext);
    }


    @Override
    public ProjectAdapter.ProjectHolder getHolder(View view) {
        return new ProjectHolder(view);
    }


    @Override
    public int getLayoutId() {
        return R.layout.project_ry_item;
    }

    @Override
    public void onBindView(ProjectHolder holder, int position) {
        if (holder != null & infos != null && infos.size() != 0) {
            String shareUser = infos.get(position).getShareUser();
            String author = infos.get(position).getAuthor();
            String time = infos.get(position).getNiceDate();
            String tittle = infos.get(position).getTitle();
            String content = infos.get(position).getDesc();
            String style = infos.get(position).getSuperChapterName() + "/" + infos.get(position).getChapterName();
            String imgUrl = infos.get(position).getEnvelopePic();
            holder.author.setText(!TextUtils.isEmpty(shareUser) ? shareUser : author);
            holder.time.setText(time);
            holder.tittle.setText(tittle);
            holder.content.setText(content);
            holder.style.setText(style);
            Glide.with(mContext).load(imgUrl).apply(RequestOptions.bitmapTransform(new RoundedCorners(1))).into(holder.img);
            holder.setPosition(position);
        }
    }

    class ProjectHolder extends BaseAdapter.BaseHolder {
        private TextView author;
        private TextView time;
        private TextView tittle;
        private TextView content;
        private TextView style;
        private ImageView img;

        public ProjectHolder(@NonNull View itemView) {
            super(itemView);
            author = (TextView) itemView.findViewById(R.id.project_ry_item_author);
            time = (TextView) itemView.findViewById(R.id.project_ry_item_time);
            tittle = (TextView) itemView.findViewById(R.id.project_ry_item_tittle);
            content = (TextView) itemView.findViewById(R.id.project_ry_item_content);
            style = (TextView) itemView.findViewById(R.id.project_ry_item_style);
            img = (ImageView) itemView.findViewById(R.id.project_ry_item_img);
        }

    }


}
