package com.hsns.picture.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public abstract class BaseAdapter<T1,T2 extends BaseAdapter.BaseHolder> extends RecyclerView.Adapter<T2> {
    public List<T1> infos;
    public Context mContext;
    private BaseAdapter.onItemClickListener listener;


    public BaseAdapter(List<T1> infos, Context mContext) {
        this.infos = infos;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public T2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);
        return getHolder(view);
    }

    /**
     * 获取对应的holder对象
     * @param view
     * @return holder
     */
    public abstract T2 getHolder(View view);

    /**
     * 获取对应的layout id
     *
     * @return layout id
     */
    public abstract int getLayoutId();

    /**
     * 绑定item数据
     * @param holder  item子view对象
     * @param position 位置
     */
    public abstract void onBindView(T2 holder, int position);


    @Override
    public void onBindViewHolder(@NonNull T2 holder, int position) {
        if (holder != null) {
            holder.setPosition(position);
            onBindView(holder,position);
        }
    }

    @Override
    public int getItemCount() {
        return infos != null && infos.size() != 0 ? infos.size() : 0;
    }

    public class BaseHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private int position;

        public BaseHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onChildItemClick(v, position);
            }
        }

        public void setPosition(int position) {
            this.position = position;
        }
    }


    /**
     * 点击事件监听
     */
    public interface onItemClickListener {
        void onChildItemClick(View root, int position);
    }

    public void setChildItemClickListener(BaseAdapter.onItemClickListener listener) {
        this.listener = listener;
    }
}
