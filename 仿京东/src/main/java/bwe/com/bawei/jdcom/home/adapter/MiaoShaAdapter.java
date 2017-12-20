package bwe.com.bawei.jdcom.home.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import bwe.com.bawei.jdcom.R;
import bwe.com.bawei.jdcom.home.Bean.BannerBean;

/**
 * Created by Zhang on 2017/11/14.
 */

public class MiaoShaAdapter extends RecyclerView.Adapter<MiaoShaAdapter.MyViewHolder> {

    private List<BannerBean.MiaoshaBean.ListBeanX> list;
    private Context context;

    public MiaoShaAdapter(List<BannerBean.MiaoshaBean.ListBeanX> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.msitem, parent, false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        BannerBean.MiaoshaBean.ListBeanX ms = list.get(position);
        holder.msTv.setText(ms.getBargainPrice()+"");
        String images = ms.getImages();
        String[] split = images.split("\\|");
        Uri uri = Uri.parse(split[0]);
        holder.msImg.setImageURI(uri);
        holder.msTvy.setText((ms.getBargainPrice()+ms.getPrice())+"");
        holder.msTvy.setPaintFlags(holder.msTvy.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder  {
        @BindView(R.id.ms_img)
        ImageView msImg;
        @BindView(R.id.ms_tv)
        TextView msTv;
        @BindView(R.id.ms_tvy)
        TextView msTvy;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
