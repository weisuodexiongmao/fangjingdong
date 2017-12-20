package bwe.com.bawei.jdcom.shopcar.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.testten.R;
import com.bwie.testten.mine.view.LoginActivity;
import com.bwie.testten.shopcar.ShopCarConstract;
import com.bwie.testten.shopcar.adapter.ShopCarAdapter;
import com.bwie.testten.shopcar.bean.DeleteBean;
import com.bwie.testten.shopcar.bean.ShopCarBean;
import com.bwie.testten.shopcar.bean.ShopCarEvent;
import com.bwie.testten.shopcar.pay.PayDemoActivity;
import com.bwie.testten.shopcar.presenter.ShopCarPresenter;
import com.bwie.testten.utils.Api;
import com.bwie.testten.utils.Toasts;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Zhang on 2017/11/9.
 */

public class ShopCarFragment extends Fragment implements ShopCarConstract.ICarView {

    @BindView(R.id.tv_keepout)
    TextView tvKeepout;

    Unbinder unbinder;
    @BindView(R.id.car_rcv)
    RecyclerView carRcv;
    @BindView(R.id.ck_all)
    CheckBox ckAll;
    @BindView(R.id.all_price)
    TextView allPrice;
    @BindView(R.id.pay)
    Button pay;
    @BindView(R.id.tv_carnull)
    TextView tvCarnull;
    private String name;
    private int uid;
    private String pwd;
    private ShopCarPresenter shopCarPresenter;
    private ShopCarAdapter shopCarAdapter;
    List<ShopCarBean.DataBean> dblist;
    int pc = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.shopcar, container, false);
        unbinder = ButterKnife.bind(this, v);
        EventBus.getDefault().register(this);
        shopCarPresenter = new ShopCarPresenter(this);
        initdata();

        return v;
    }

    private void initdata() {
        ckAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ckAll.isChecked()) {
                    ckAll.setChecked(true);
                    shopCarAdapter.ckall();
                } else {
                    ckAll.setChecked(false);
                    shopCarAdapter.cknull();
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences user = getActivity().getSharedPreferences("USER", Context.MODE_PRIVATE);
        name = user.getString("name", "000");
        uid = user.getInt("uid", 0);
        pwd = user.getString("pwd", "000");
        if (name.equals("000")) {
            tvKeepout.setVisibility(View.VISIBLE);
            tvCarnull.setVisibility(View.GONE);
        } else {
            tvKeepout.setVisibility(View.GONE);
            tvCarnull.setVisibility(View.VISIBLE);
        }
        shopCarPresenter.LoadList(Api.BANNERURL, uid);
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED, sticky = true)
    public void getpc(ShopCarEvent shopCarEvent) {
        float price = shopCarEvent.getPrice();
        int position = shopCarEvent.getPosition();
        if (dblist != null) {
            ShopCarBean.DataBean dataBean = dblist.get(position);
            if (dataBean.isCheck()) {
                pc += price;
            } else {
                pc -= price;
            }

        }
        allPrice.setText("共计：" + pc + "元");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
        shopCarPresenter = null;
    }

    @Override
    public void ShowList(final List<ShopCarBean.DataBean> list) {
        if(list!=null){
            tvCarnull.setVisibility(View.GONE);
        }else{
            tvCarnull.setVisibility(View.VISIBLE);
        }
        if (list != null) {
            dblist = new ArrayList<>();
            dblist.addAll(list);
        }else{
            dblist = null;
        }
        shopCarAdapter = new ShopCarAdapter(list, getActivity());
        carRcv.setAdapter(shopCarAdapter);
        carRcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        shopCarAdapter.setAllCk(new ShopCarAdapter.AllCk() {
            @Override
            public void all(boolean b) {
                ckAll.setChecked(b);
            }
        });
        shopCarAdapter.setOnDeleteListener(new ShopCarAdapter.OnDeleteListener() {
            @Override
            public void onDelete(View v, int position) {
                //Toasts.showLong(getActivity(),"删除？"+position);

                //Toasts.showLong(getActivity(),pid+"");
                shopCarPresenter.LoadDelete(Api.BANNERURL, list.get(position).getList().get(0).getPid(), uid);
                //shopCarAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void ShowEnd() {
       // dblist = null;
    }

    @Override
    public void ShowError(String e) {
        Toasts.showLong(getActivity(), e);
        Log.e("hahahahahahahahahaha", e);
    }

    @Override
    public void ShowDelete(DeleteBean db) {
        Toasts.showLong(getActivity(), "" + db.getMsg());
        //dblist.clear();
        shopCarPresenter.LoadList(Api.BANNERURL, uid);
        //shopCarAdapter.notifyDataSetChanged();
    }

    @Override
    public void ShowDeleteError(String e) {
        Toasts.showLong(getActivity(), e);
        Log.e("哈哈哈哈哈哈哈哈哈", e);
    }

    @OnClick({R.id.tv_keepout, R.id.pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_keepout:
                Intent in = new Intent(getActivity(), LoginActivity.class);
                startActivity(in);
                break;
            case R.id.pay:
                Intent inpay = new Intent(getActivity(), PayDemoActivity.class);
                startActivity(inpay);
                break;
        }
    }
}
