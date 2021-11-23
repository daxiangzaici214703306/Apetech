package com.hsns.picture.userprofile.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hsns.base.bean.CoinRankInfo;
import com.hsns.picture.PictureApplication;
import com.hsns.picture.R;
import com.hsns.picture.home.adapter.BaseAdapter;

import java.util.List;


public class CoinRankAdapter extends BaseAdapter<CoinRankInfo.Datas, CoinRankAdapter.CoinRankHolder> {

    public CoinRankAdapter(List<CoinRankInfo.Datas> infos, Context mContext) {
        super(infos, mContext);
    }


    @Override
    public CoinRankAdapter.CoinRankHolder getHolder(View view) {
        return new CoinRankHolder(view);
    }


    @Override
    public int getLayoutId() {
        return R.layout.rank_ry_item;
    }

    @Override
    public void onBindView(CoinRankHolder holder, int position) {
        if (holder != null & infos != null && infos.size() != 0) {
            String rank = infos.get(position).getRank();
            String name = infos.get(position).getUsername();
            int level = infos.get(position).getLevel();
            int count = infos.get(position).getCoinCount();
            holder.num.setText(rank);
            holder.name.setText(name);
            holder.level.setText(PictureApplication.getApplication().getString(R.string.coinlevel) + level);
            holder.count.setText(PictureApplication.getApplication().getString(R.string.coincount) + count);
        }
    }

    class CoinRankHolder extends BaseAdapter.BaseHolder {
        private TextView num;
        private TextView name;
        private TextView level;
        private TextView count;


        public CoinRankHolder(@NonNull View itemView) {
            super(itemView);
            num = (TextView) itemView.findViewById(R.id.rank_num);
            name = (TextView) itemView.findViewById(R.id.rank_name);
            level = (TextView) itemView.findViewById(R.id.rank_level);
            count = (TextView) itemView.findViewById(R.id.rank_count);
        }

    }


}
