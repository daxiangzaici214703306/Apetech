package com.hsns.picture.photos.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.hsns.picture.R;

import java.io.File;
import java.util.List;


public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder> {
    private List<String> photos;
    private Context mContext;

    public PhotoAdapter(List<String> photos, Context mContext) {
        this.photos = photos;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public PhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_ry_item, parent,false);
        PhotoHolder holder = new PhotoHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoHolder holder, int position) {
        if (holder != null && mContext != null & photos != null && photos.size() != 0) {
            //新版Glide(4.6.0 之后)  需要设置options
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.mipmap.loading)
                    .error(R.mipmap.loading_error)
                    .priority(Priority.HIGH)
                    .diskCacheStrategy(DiskCacheStrategy.NONE);
            Glide.with(mContext).load(Uri.fromFile(new File(photos.get(position)))).apply(options).into(holder.photoImg);
        }
    }

    @Override
    public int getItemCount() {
        return photos != null && photos.size() != 0 ? photos.size() : 0;
    }

    class PhotoHolder extends RecyclerView.ViewHolder {
        private ImageView photoImg;

        public PhotoHolder(@NonNull View itemView) {
            super(itemView);
            photoImg = (ImageView) itemView.findViewById(R.id.photo_ry_img);
        }
    }
}
