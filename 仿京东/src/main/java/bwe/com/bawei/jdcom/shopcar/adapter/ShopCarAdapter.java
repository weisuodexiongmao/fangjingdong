package bwe.com.bawei.jdcom.shopcar.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bwie.testten.R;
import com.bwie.testten.shopcar.bean.ShopCarBean;
import com.bwie.testten.shopcar.bean.ShopCarEvent;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Zhang on 2017/11/18.
 */

public class ShopCarAdapter extends RecyclerView.Adapter<ShopCarAdapter.MyViewHolder> {

    private List<ShopCarBean.DataBean> list;
    private Context context;
    private OnDeleteListener onDeleteListener;

    public OnDeleteListener getOnDeleteListener() {
        return onDeleteListener;
    }

    public void setOnDeleteListener(OnDeleteListener onDeleteListener) {
        this.onDeleteListener = onDeleteListener;
    }

    private AllCk allCk;

    public AllCk getAllCk() {
        return allCk;
    }

    public void setAllCk(AllCk allCk) {
        this.allCk = allCk;
    }


    public interface OnDeleteListener{
        void onDelete(View v, int position);
    }

    public interface AllCk {
        void all(boolean b);
    }


    public ShopCarAdapter(List<ShopCarBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.caritem, parent, false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final ShopCarBean.DataBean dataBean = list.get(position);
        final List<ShopCarBean.DataBean.ListBean> lb = dataBean.getList();
        for (int i = 0; i < lb.size(); i++) {
            final ShopCarBean.DataBean.ListBean ll = lb.get(i);

            holder.carTitle.setText(ll.getTitle());
            holder.carPrice.setText("￥ " + ll.getPrice());
            String images = ll.getImages();
            if (images != null) {
                String[] split = images.split("\\|");
                Uri uri = Uri.parse(split[0]);
                holder.carImg.setImageURI(uri);
            }
            holder.carCk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.carCk.isChecked()) {
                        EventBus.getDefault().postSticky(new ShopCarEvent(ll.getPrice(), position));
                    } else {
                        EventBus.getDefault().postSticky(new ShopCarEvent(ll.getPrice(), position));
                    }
                }
            });

            holder.carDele.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDeleteListener.onDelete(v,position);
                    notifyDataSetChanged();
                }
            });
        }
        holder.carCk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataBean.setCheck(isChecked);
                if (isChecked) {
                    allCk.all(isAllChecked());
                    //EventBus.getDefault().postSticky(new ShopCarEvent(peice,position));
                } else {
                    allCk.all(false);
                    // EventBus.getDefault().postSticky(new ShopCarEvent(peice,position));
                }
            }
        });
        holder.carCk.setChecked(dataBean.isCheck());
        //holder.carTitle.setText(dataBean.getSellerName());

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    private boolean isAllChecked() {
        for (int i = 0; i < list.size(); i++) {
            ShopCarBean.DataBean dataBean = list.get(i);
            if (!dataBean.isCheck()) {
                return false;
            }
        }
        return true;
    }

    public void ckall() {
        for (int i = 0; i < list.size(); i++) {
            ShopCarBean.DataBean dataBean = list.get(i);
            dataBean.setCheck(true);
            notifyDataSetChanged();
        }
    }

    public void cknull() {
        for (int i = 0; i < list.size(); i++) {
            ShopCarBean.DataBean dataBean = list.get(i);
            dataBean.setCheck(false);
            notifyDataSetChanged();
        }
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.car_ck)
        CheckBox carCk;
        @BindView(R.id.car_img)
        SimpleDraweeView carImg;
        @BindView(R.id.car_title)
        TextView carTitle;
        @BindView(R.id.car_price)
        TextView carPrice;
        @BindView(R.id.car_dele)
        Button carDele;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
