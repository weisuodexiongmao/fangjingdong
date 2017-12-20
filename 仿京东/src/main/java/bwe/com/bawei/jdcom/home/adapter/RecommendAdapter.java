package bwe.com.bawei.jdcom.home.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import bwe.com.bawei.jdcom.R;
import bwe.com.bawei.jdcom.home.Bean.BannerBean;

/**
 * Created by Zhang on 2017/11/15.
 */

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.MyViewHolder> {

    private List<BannerBean.TuijianBean.ListBean> list;
    private Context context;

    public RecommendAdapter(List<BannerBean.TuijianBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tjitem, null, false));

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final BannerBean.TuijianBean.ListBean tj = list.get(position);
        String images = tj.getImages();
        String[] split = images.split("\\|");

        Uri uri = Uri.parse(split[0]);
        holder.tjImg.setImageURI(uri);
        holder.tjTitle.setText(tj.getTitle());
        holder.tjPrice.setText("￥ " + tj.getBargainPrice() + "元");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toasts.showLong(context,tj.getTitle()+position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tj_img)
        SimpleDraweeView tjImg;
        @BindView(R.id.tj_title)
        TextView tjTitle;
        @BindView(R.id.tj_price)
        TextView tjPrice;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
