package bwe.com.bawei.jdcom.classify.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import bwe.com.bawei.jdcom.R;
import bwe.com.bawei.jdcom.classify.bean.TwoBean;
import bwe.com.bawei.jdcom.home.RecommendRecyclerView;

/**
 * Created by Zhang on 2017/11/16.
 */

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.MyViewHodler> {

    int flag = 0;
    private List<TwoBean.DataBean> list;
    private Context context;

    public RightAdapter(List<TwoBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHodler myViewHodler = new MyViewHodler(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rightitem, parent, false));

        return myViewHodler;
    }

    @Override
    public void onBindViewHolder(final MyViewHodler holder, int position) {

        TwoBean.DataBean db = list.get(position);
        holder.tvTwoname.setText(db.getName());
        List<TwoBean.DataBean.ListBean> list = db.getList();
        ChildAdapter childAdapter = new ChildAdapter(list,context);
        holder.tworcv.setAdapter(childAdapter);
        holder.tworcv.setLayoutManager(new GridLayoutManager(context,3));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (flag){
                    case 0:
                        holder.tworcv.setVisibility(View.GONE);
                        flag = 1;
                        break;
                    case 1:
                        holder.tworcv.setVisibility(View.VISIBLE);
                        flag = 0;
                        break;
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }


    static class MyViewHodler extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_twoname)
        TextView tvTwoname;
        @BindView(R.id.tworcv)
        RecommendRecyclerView tworcv;

        public MyViewHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
