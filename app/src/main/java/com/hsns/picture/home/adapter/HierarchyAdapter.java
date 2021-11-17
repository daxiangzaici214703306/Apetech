package com.hsns.picture.home.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hsns.base.bean.HierarchyInfo;
import com.hsns.base.bean.HomeInfo;
import com.hsns.picture.R;

import java.util.List;


public class HierarchyAdapter extends BaseAdapter<HierarchyInfo.Data, HierarchyAdapter.HierarchyHolder> {

    public HierarchyAdapter(List<HierarchyInfo.Data> infos, Context mContext) {
        super(infos, mContext);
    }


    @Override
    public HierarchyAdapter.HierarchyHolder getHolder(View view) {
        return new HierarchyHolder(view);
    }


    @Override
    public int getLayoutId() {
        return R.layout.hieraychy_ry_item;
    }

    @Override
    public void onBindView(HierarchyHolder holder, int position) {
        if (holder != null & infos != null && infos.size() != 0) {
            String tittle = infos.get(position).getName();
            List<HierarchyInfo.Children> childrenList = infos.get(position).getChildren();
            StringBuilder childs = new StringBuilder();
            for (HierarchyInfo.Children children : childrenList) {
                childs.append(children.getName() + "/");
            }
            holder.tittle.setText(tittle);
            holder.child1.setText(childs.toString());
        }
    }

    class HierarchyHolder extends BaseAdapter.BaseHolder {
        private TextView tittle;
        private TextView child1;



        public HierarchyHolder(@NonNull View itemView) {
            super(itemView);
            tittle = (TextView) itemView.findViewById(R.id.hierarchy_ry_item_tittle);
            child1 = (TextView) itemView.findViewById(R.id.hierarchy_ry_item_child_1);

        }

    }


}
