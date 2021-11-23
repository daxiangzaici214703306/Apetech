package com.hsns.picture.userprofile.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hsns.base.bean.PersonCoinInfo;
import com.hsns.picture.R;
import com.hsns.picture.home.adapter.BaseAdapter;

import java.util.List;


public class PerCoinAdapter extends BaseAdapter<PersonCoinInfo.Datas, PerCoinAdapter.PerCoinHolder> {

    public PerCoinAdapter(List<PersonCoinInfo.Datas> infos, Context mContext) {
        super(infos, mContext);
    }


    @Override
    public PerCoinAdapter.PerCoinHolder getHolder(View view) {
        return new PerCoinHolder(view);
    }


    @Override
    public int getLayoutId() {
        return R.layout.rank_ry_item;
    }

    @Override
    public void onBindView(PerCoinHolder holder, int position) {
        if (holder != null & infos != null && infos.size() != 0) {
            String rank = (position+1)+"";
            String name = infos.get(position).getDesc();
            int count = infos.get(position).getCoinCount();
            holder.num.setText(rank);
            holder.name.setText(name);
            holder.count.setText("+" + count);
        }
    }

    class PerCoinHolder extends BaseAdapter.BaseHolder {
        private TextView num;
        private TextView name;
        private TextView level;
        private TextView count;


        public PerCoinHolder(@NonNull View itemView) {
            super(itemView);
            num = (TextView) itemView.findViewById(R.id.rank_num);
            name = (TextView) itemView.findViewById(R.id.rank_name);
            level = (TextView) itemView.findViewById(R.id.rank_level);
            count = (TextView) itemView.findViewById(R.id.rank_count);
            count.setTextColor(Color.BLUE);
        }

    }


}
